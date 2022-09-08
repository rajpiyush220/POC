package com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@NoArgsConstructor // required for hibernate but shouldn't be used otherwise
@MappedSuperclass
@Getter
@Setter
public class Mutable extends Versioned {

  @Id private UUID id = UUID.randomUUID();
}
