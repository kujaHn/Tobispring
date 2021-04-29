package com.tobispring.book.service;

import com.tobispring.book.dao.UserDao;
import com.tobispring.book.domain.Level;
import com.tobispring.book.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import javax.websocket.Session;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    private UserDao userDao;

    private PlatformTransactionManager transactionManager;

    @Autowired
    public UserService(UserDao userDao, PlatformTransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

/** XML을 사용하면 이 방법을 이용하면 된다.
 *  //DataSource DI
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    //UserDao DI
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
 */

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
            userDao.add(user);
        }
    }

    public void upgradeLevels() throws SQLException {
        // 동기화 작업 초기화
        TransactionStatus status = this.transactionManager.getTransaction(
                new DefaultTransactionDefinition());

        try {
            List<User> users = userDao.getAll();
            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    upgradeLevel(user);
                }
            }
            this.transactionManager.commit(status);
        } catch (Exception e) {
            this.transactionManager.rollback(status);

            throw e;
        } finally {
//            DataSourceUtils.releaseConnection(c, dataSource);
            // 동기화 작업 종료 및 정리
//            TransactionSynchronizationManager.unbindResource(this.dataSource);
//            TransactionSynchronizationManager.clearSynchronization();
        }
    }

    public void upgradeLevel(User user) {
//        if (user.getLevel() == Level.BASIC) {
//            user.setLevel(Level.SILVER);
//        } else if (user.getLevel() == Level.SILVER) {
//            user.setLevel(Level.GOLD);
//        }
//        userDao.update(user);
        user.upgradeLevel();
        userDao.update(user);
//        sendUpgradeEmail(user);
    }

//    JavaMail API -> 확장성이라곤 찾아볼 수 없는 악명높은 API
//    private void snedUpgradeEmail(User user) {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", "mail.ksug,org");
//        javax.mail.Session.getInstance(properties, null);
//    }

/*    private void sendUpgradeEMail(User user) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("useradmin@ksug.org");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText("사용자님의 등급이 " + user.getLevel().name());

        this.mailSender.send(mailMessage);
    }*/

    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC: return (user.getLogin() >= MIN_LOGIN_COUNT_FOR_SILVER);
            case SILVER: return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD: return false;
            default: throw new IllegalArgumentException("Unknown Level: " + currentLevel);
        }

    }
}
