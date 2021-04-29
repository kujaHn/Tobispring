package com.tobispring.book.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Level {

    GOLD(3, null), SILVER(2, GOLD), BASIC(1, SILVER);

    private final int value;
    private final Level next;

    public int intValue() {
        return value;
    }

    //getter
    public Level nextLevel() {
        return this.next;
    }

    public static Level valueOf(int value) {
        switch (value) {
            case 1: return BASIC;
            case 2: return SILVER;
            case 3: return GOLD;
            default:
                throw new Error("Unknown value: " + value);
        }
    }
}
