package com.touchblankspot.video.viewer.data.repository;

import com.touchblankspot.video.viewer.data.model.YoutubeVideoDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface YoutubeVideoDetailRepository extends CrudRepository<YoutubeVideoDetail, UUID> {
}
