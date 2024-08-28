package com.touchblankspot.auth.jwt.data.model;

import com.touchblankspot.auth.jwt.data.embedded.Mutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Mutable {

  @Size(min = 6, max = 50, message = "Minimum username length: 6 characters")
  @Column(name = "username", unique = true, nullable = false)
  private String userName;

  @Column(name = "email", unique = true, nullable = false)
  @Size(min = 5, max = 60)
  @Email
  private String email;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  @Column(name = "password")
  private String password;

  @Column(name = "contact_no")
  @Size(min = 10, max = 30)
  private String contactNo;

  @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
  private Set<Role> roles;
}
