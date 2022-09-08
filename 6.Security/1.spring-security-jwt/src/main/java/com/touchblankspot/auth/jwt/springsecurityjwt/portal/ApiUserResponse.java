package com.touchblankspot.auth.jwt.springsecurityjwt.portal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class ApiUserResponse {

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("username")
  private String userName;

  @JsonProperty("email")
  private String email;

  @JsonIgnore private String password;

  @JsonProperty("contact_no")
  private String contactNo;

  @JsonIgnore private Set<Role> roles;

  @JsonProperty("roles")
  private List<String> userRoles;
}
