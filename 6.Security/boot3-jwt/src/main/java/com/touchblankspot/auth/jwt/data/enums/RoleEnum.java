package com.touchblankspot.auth.jwt.data.enums;

public enum RoleEnum {

    SUPER_ADMIN("SUPER_ADMIN"),
    ADMIN("ADMIN"),
    USER("USER");

    private String name;

    RoleEnum(String roleName) {
        this.name = roleName;
    }

    public String getName() {
        return name;
    }
}
