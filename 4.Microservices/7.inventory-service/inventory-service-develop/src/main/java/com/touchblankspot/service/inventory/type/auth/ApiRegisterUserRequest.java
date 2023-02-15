package com.touchblankspot.service.inventory.type.auth;

import com.touchblankspot.common.validator.FieldMatch;
import com.touchblankspot.common.validator.UniqueField;
import com.touchblankspot.common.validator.ValidPassword;
import com.touchblankspot.service.inventory.service.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(
    first = "password",
    second = "passwordConfirm",
    message = "Password and confirm password doesn't match")
public class ApiRegisterUserRequest {

  @Size(min = 2, max = 30, message = "FirstName must be between 2 and 30 character.")
  protected String firstName;

  @Size(max = 30, message = "LastName must be less than 30 character.")
  protected String lastName;

  @Email(message = "Username should be a valid email address.")
  @Size(min = 6, max = 32, message = "Username must be between 6 and 32 character.")
  @UniqueField(
      service = UserService.class,
      fieldName = "userName",
      message = "Selected Username already taken.")
  protected String userName;

  @ValidPassword protected String password;

  @ValidPassword protected String passwordConfirm;

  private String roleName;
}
