package com.touch.blankspot.user.portal.data.model;

import com.touch.blankspot.common.data.embedded.Immutable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
