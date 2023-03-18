package com.touchblankspot.youtube.player.batch;

import com.touchblankspot.youtube.player.service.YoutubeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ScheduleTask {

    @NonNull
    private final YoutubeService youtubeService;

    @Scheduled(cron = "5 3 * * * *", zone = "Asia/Kolkata")
    public void storeDailyVideos() {
        log.info("Daily video storage job started at {}", LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
        LocalDate executionDate = LocalDate.now(ZoneId.of("Asia/Kolkata")).minusDays(1);
        log.info("Daily video storage job started for {}", executionDate);
        int videoCount = youtubeService.storeDailyVideo(executionDate);
        log.info("Daily video storage job completed for {} and updated {} video.", executionDate, videoCount);
        log.info("Daily video storage job finished at {}", LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
    }
}
