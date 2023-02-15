package com.touch.blankspot.oauth.web.service.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AppRegistrationRequest {

  private String email;
  private String password;
}
