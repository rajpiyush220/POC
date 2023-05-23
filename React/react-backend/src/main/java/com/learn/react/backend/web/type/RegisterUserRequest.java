package com.learn.react.backend.web.type;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String userName;

    private String firstName;

    private String lastName;

    private String password;
}
