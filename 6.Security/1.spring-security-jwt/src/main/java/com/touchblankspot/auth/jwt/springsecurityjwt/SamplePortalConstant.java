package com.touchblankspot.auth.jwt.springsecurityjwt;

public interface SamplePortalConstant {

  String USER_PORTAL_PATH = "/security-jwt";

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
