package com.touchblankspot.youtube.player.batch;

import com.touchblankspot.youtube.player.service.YoutubeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
        int videoCount = youtubeService.PullLatestVideos();
        log.info("Daily video storage job completed and updated {} video.", videoCount);
        log.info("Daily video storage job finished at {}", LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
    }
}
