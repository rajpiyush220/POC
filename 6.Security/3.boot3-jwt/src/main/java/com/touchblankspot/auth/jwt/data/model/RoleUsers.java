package com.touchblankspot.auth.jwt.data.model;

import com.touchblankspot.auth.jwt.data.embedded.Immutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
