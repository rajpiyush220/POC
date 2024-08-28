package com.touchblankspot.auth.jwt.security.type;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
public class AuthenticationRequest implements Serializable {

  @NonNull private String username;

  @NonNull private String password;
}
