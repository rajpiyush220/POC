package com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.embedded;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
@NoArgsConstructor // required for hibernate but shouldn't be used otherwise
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
  private OffsetDateTime created =
      OffsetDateTime.now()
          .truncatedTo(ChronoUnit.MICROS); // To match with column definition datetime(6)
}
