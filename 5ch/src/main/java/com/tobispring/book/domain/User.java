package com.tobispring.book.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String id;

    private String name;

    private String password;

    private String email;

    private Level level;

    private int login;

    private int recommend;

    public User(String id, String name, String password, String email, Level level, int login, int recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.level = level;
        this.login = login;
        this.recommend = recommend;
    }

    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if (nextLevel == null) {
            throw new IllegalStateException(this.level + "은  업그레이드가 불가능합니다");
        }
        else {
            this.level = nextLevel;
        }
    }
}
