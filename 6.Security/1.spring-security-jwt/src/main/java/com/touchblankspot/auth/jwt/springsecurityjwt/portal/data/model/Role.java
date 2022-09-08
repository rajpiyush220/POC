package com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model;

import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.embedded.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Immutable {

  @Column(name = "name", updatable = false, nullable = false, length = 20, unique = true)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<User> users;
}
