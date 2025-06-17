package com.bank.common;

public enum RoleEnum {
    ADMIN("ADM", "Admin"),
    USER("USR", "User");

    private final String code;
    private final String name;

    RoleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
