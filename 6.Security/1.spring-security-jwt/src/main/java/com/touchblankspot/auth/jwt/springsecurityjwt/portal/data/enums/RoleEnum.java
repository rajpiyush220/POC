package com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.enums;

public enum RoleEnum {

    SUPER_ADMIN("SUPER_ADMIN"),
    ADMIN("ADMIN"),
    USER("USER");

    private String name;

    RoleEnum(String roleName) {
        this.name = roleName;
    }
}
