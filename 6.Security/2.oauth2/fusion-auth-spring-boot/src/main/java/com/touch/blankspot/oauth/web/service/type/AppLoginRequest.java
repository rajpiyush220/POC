package com.touch.blankspot.oauth.web.service.type;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AppLoginRequest {

  @NotNull(message = "Id can not be null.")
  private String id;

  @NotNull(message = "Credential can not be null.")
  private String credential;
}
