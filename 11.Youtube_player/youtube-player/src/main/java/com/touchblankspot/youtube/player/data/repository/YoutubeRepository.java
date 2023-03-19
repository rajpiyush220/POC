package com.touchblankspot.youtube.player.data.repository;

import com.touchblankspot.youtube.player.data.model.YoutubeVideoDetail;
import com.touchblankspot.youtube.player.web.type.PulledVideoResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface YoutubeRepository extends CrudRepository<YoutubeVideoDetail, UUID> {

    @Query(nativeQuery = true,
            value = "select publish_date as publishDate,count(*) as videoCount from video_details group by publish_date order by publish_date desc")
    List<Object[]> getVideoCountByPublishDate();

    List<YoutubeVideoDetail> findByIsShorts(Boolean isShorts);

    List<YoutubeVideoDetail> findAll();

    List<YoutubeVideoDetail> findByPublishDate(LocalDate localDate);
}
