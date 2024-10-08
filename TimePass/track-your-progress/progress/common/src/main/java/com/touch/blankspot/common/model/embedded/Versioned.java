package com.touch.blankspot.common.model.embedded;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import lombok.Data;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.data.annotation.ReadOnlyProperty;

@Data
@OptimisticLocking
@MappedSuperclass
public class Versioned {

  @Version @ReadOnlyProperty private Long version;

  private OffsetDateTime created =
      OffsetDateTime.now()
          .truncatedTo(ChronoUnit.MICROS); // To match with column definition datetime(6)

  private OffsetDateTime updated = created;

  @PrePersist
  public void onPrePersist() {
    setUpdated(getCreated());
  }

  @PreUpdate
  public void onPreUpdate() {
    setUpdated(OffsetDateTime.now().truncatedTo(ChronoUnit.MICROS));
  }

  public void resetCreated() {
    setCreated(OffsetDateTime.now().truncatedTo(ChronoUnit.MICROS));
    setUpdated(getCreated());
  }
}
