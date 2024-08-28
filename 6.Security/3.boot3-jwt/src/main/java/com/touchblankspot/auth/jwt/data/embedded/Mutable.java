package com.touchblankspot.auth.jwt.data.embedded;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor // required for hibernate but shouldn't be used otherwise
@MappedSuperclass
@Getter
@Setter
public class Mutable extends Versioned {

  @Id
  private UUID id = UUID.randomUUID();
}
