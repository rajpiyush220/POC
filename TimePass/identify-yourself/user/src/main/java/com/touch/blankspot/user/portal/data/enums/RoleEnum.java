package com.touch.blankspot.user.portal.data.enums;

public enum RoleEnum {

    SUPER_ADMIN("SUPER_ADMIN"),
    ADMIN("ADMIN"),
    USER("USER");

    private String name;

    RoleEnum(String roleName) {
        this.name = roleName;
    }
}
