package com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model;

import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.embedded.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity(name = "role_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleUsers extends Immutable {

  @Column(name = "users_id")
  private UUID userId;

  @Column(name = "roles_id")
  private UUID roleId;
}
