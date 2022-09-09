package com.test.springbatchmongo.batch.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskConstants {
    public static final String READ_STATUS = "read_status";
    public static final String STARTED = "started";
    public static final String COMPLETED = "completed";
    public static final String JOB_ID = "jobId";
}
