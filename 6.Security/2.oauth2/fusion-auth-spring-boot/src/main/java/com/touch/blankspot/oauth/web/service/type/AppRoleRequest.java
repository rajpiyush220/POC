package com.touch.blankspot.oauth.web.service.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AppRoleRequest {

  private String name;

  private String description;

  private boolean isDefault;

  private boolean isSuperRole;
}
