package com.touchblankspot.video.viewer.common.data.model.embedded;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Immutable {
  @Id private UUID id = UUID.randomUUID();

  @Column(nullable = false, updatable = false)
  @Version
  @ReadOnlyProperty
  private Long version;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  @JsonIgnore
  private OffsetDateTime created = OffsetDateTime.now().truncatedTo(ChronoUnit.MICROS);
}
