package com.tobispring.book.domain;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();

        //given
        User user = new User();
        user.setId(1L);
        user.setName("스프링1");
        user.setPassword("비밀번호1");

        dao.add(user);

        System.out.println(user.getId() + " 등록성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회성공");


    }
}
