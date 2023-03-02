package com.touch.blankspot.user;

public interface UserPortalConstant {

  String USER_PORTAL_PATH = "/user-portal";

  String[] PERMIT_ALL_URLS = {
    USER_PORTAL_PATH + "/login",
    USER_PORTAL_PATH + "/users/register",
    "/swagger-ui/index.html",
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/api-docs/**",
    "/resources/**"
  };
}
