package com.touch.blankspot.user.portal.apitype;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiRoleResponse {

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("name")
  private String name;
}
