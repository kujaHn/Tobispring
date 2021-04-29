package com.tobispring.book.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//NextLevel이 성공적으로 통하는지 테스트
class UserTest {

    User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test()
    public void upgradeLevel() {
        Level[] levels = Level.values();
        for(Level level : levels) {
            if (level.nextLevel() == null) continue;
            user.setLevel(level);
            System.out.println("before user.getLevel() = " + user.getLevel());
            user.upgradeLevel();
            System.out.println("after user.getLevel() = " + user.getLevel());
            assertThat(user.getLevel()).isSameAs(level.nextLevel());
            System.out.println("level.nextLevel() = " + level.nextLevel());
        }
    }

    @Test
    public void cannotUpgradeLevel() {
        Level[] levels = Level.values();
        for(Level level : levels) {
            if (level.nextLevel() != null) continue;
            user.setLevel(level);
            assertThrows(IllegalStateException.class, () -> user.upgradeLevel());
        }

    }
}