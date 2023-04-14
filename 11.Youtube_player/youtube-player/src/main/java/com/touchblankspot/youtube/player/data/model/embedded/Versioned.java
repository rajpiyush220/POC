package com.touchblankspot.youtube.player.data.model.embedded;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.Data;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Data
@OptimisticLocking
@MappedSuperclass
public class Versioned {

	@Version
	@ReadOnlyProperty
	private Long version;

	private OffsetDateTime created = OffsetDateTime.now().truncatedTo(ChronoUnit.MICROS);

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
