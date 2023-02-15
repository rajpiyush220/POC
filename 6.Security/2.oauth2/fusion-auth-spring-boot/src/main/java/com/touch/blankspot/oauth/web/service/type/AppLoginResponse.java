package com.touch.blankspot.oauth.web.service.type;

import io.fusionauth.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AppLoginResponse {
  private String token;
  private String refreshToken;
  private User user;
}
