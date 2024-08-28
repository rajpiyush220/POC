package com.touchblankspot.auth.jwt.constant;

public interface AppConstant {

    String JWT_AUTH_PATH = "/jwt-auth";

    String LOGIN_ENDPOINT = JWT_AUTH_PATH + "/login";
    String REGISTRATION_ENDPOINT = JWT_AUTH_PATH + "/users/register";

    String[] PERMIT_ALL_URLS = {
            LOGIN_ENDPOINT,
            REGISTRATION_ENDPOINT,
            "/swagger-ui/index.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api-docs/**",
            "/resources/**"
    };

    String[] PERMIT_CSRF_URLS = {
            LOGIN_ENDPOINT,
            REGISTRATION_ENDPOINT
    };
}
