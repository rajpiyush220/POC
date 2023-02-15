package com.touch.blankspot.oauth.constant;

public class FusionauthConstant {

  private static final String API_BASE_ENDPOINT = "/api/v1/";

  private static final String ENDPOINT_FORMAT = API_BASE_ENDPOINT.concat("%s");

  public static final String[] PERMIT_ALL = {
    String.format(ENDPOINT_FORMAT, "anyone"),
    String.format(ENDPOINT_FORMAT, "login"),
    String.format(ENDPOINT_FORMAT, "logout/**"),
    "/swagger-ui/**",
    "/v3/api-docs/**",
  };
}
