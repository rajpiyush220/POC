package com.touchblankspot.auth.jwt.data.model;

import com.touchblankspot.auth.jwt.data.embedded.Immutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
