package com.touchblankspot.service.inventory.apitype.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.touchblankspot.common.masking.annotation.Mask;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
@NoArgsConstructor
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class RegisterUserResponse {

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("userName")
  private String userName;

  @Mask
  @JsonProperty("password")
  private String password;

  @JsonProperty("roles")
  private Set<String> roles;

}
