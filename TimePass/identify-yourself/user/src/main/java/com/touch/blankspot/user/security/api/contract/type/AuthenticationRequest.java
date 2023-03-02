package com.touch.blankspot.user.security.api.contract.type;

import java.io.Serializable;
import lombok.Data;
import lombok.NonNull;

@Data
public class AuthenticationRequest implements Serializable {

  @NonNull private String username;

  @NonNull private String password;
}
