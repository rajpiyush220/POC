package com.touch.blankspot.progress.portal.data.model;

import com.touch.blankspot.common.model.embedded.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity(name = "task_action")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAction extends Immutable {

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "start_time")
    OffsetDateTime startTime;

    @Column(name = "end_time")
    OffsetDateTime endTime;

    @Column(name = "spent_hour")
    private BigDecimal spentHour = new BigDecimal("1");

    @Column(name = "description")
    private String description;
}
