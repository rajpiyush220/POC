package com.touch.blankspot.progress.portal.data.model;

import com.touch.blankspot.common.model.embedded.Mutable;
import com.touch.blankspot.progress.portal.data.enums.TaskComplexityLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends Mutable {

    @Column(name = "creator_id")
    private UUID creatorId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TaskCategory.class)
    @JoinColumn(name = "category_id")
    private TaskCategory category;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    OffsetDateTime startDate;

    @Column(name = "end_date")
    OffsetDateTime endDate;

    @Column(name = "is_strict_deadline")
    private Boolean isStrictDeadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "complexity")
    private TaskComplexityLevel complexity;

    @Column(name = "expected_hour")
    private BigDecimal expectedHour = new BigDecimal("1.0");

    @OneToMany(targetEntity = TaskAction.class, orphanRemoval = true, mappedBy = "task")
    List<TaskAction> taskActions = new ArrayList<>();
}
