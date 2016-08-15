package com.pr.base;

/**
 * @author Iuliana Cosmina
 */
public enum Gender {

    MALE(1),
    FEMALE(2);

    private int code;

    Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public Character getInitial() {
        return this.name().charAt(0);
    }

}
