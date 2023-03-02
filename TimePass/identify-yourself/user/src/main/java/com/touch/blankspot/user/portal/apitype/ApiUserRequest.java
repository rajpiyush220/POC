package com.touch.blankspot.user.portal.apitype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.touch.blankspot.user.portal.data.enums.RoleEnum;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ApiUserRequest {

  @JsonProperty("username")
  @Size(min = 6, max = 50, message = "Minimum username length: 6 characters")
  private String userName;

  @JsonProperty("email")
  @Size(min = 5, max = 60)
  @Email
  private String email;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  @JsonProperty("password")
  private String password;

  @JsonProperty("contact_no")
  @Size(min = 10, max = 30)
  private String contactNo;

  @JsonProperty("roles")
  private Set<RoleEnum> roles;
}
