package com.touch.blankspot.common.model.embedded;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor // required for hibernate but shouldn't be used otherwise
@MappedSuperclass
@Getter
@Setter
public class Mutable extends Versioned {

  @Id private UUID id = UUID.randomUUID();
}
