package com.learn.react.backend.web.type;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {

    private UUID userId;

    private String userName;

    private String firstName;

    private String lastName;

    private String password;
}
