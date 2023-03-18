package com.touchblankspot.video.viewer.common.data.model.embedded;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
public class Mutable extends Versioned {

  @Id private UUID id = UUID.randomUUID();
}
