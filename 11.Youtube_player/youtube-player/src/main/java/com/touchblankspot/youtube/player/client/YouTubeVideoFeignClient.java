package com.touchblankspot.youtube.player.client;

import com.touchblankspot.youtube.player.client.types.VideoDurationResponse;
import com.touchblankspot.youtube.player.client.types.VideoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "videos", url = "${feign.url}")
public interface  YouTubeVideoFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    VideoResponse search(@RequestParam(value="key") String key,
                         @RequestParam(value="channelId") String channelId,
                         @RequestParam(value="part") String part,
                         @RequestParam(value="order") String order,
                         @RequestParam(value="type") String type,
                         @RequestParam(value="maxResults") Integer maxResults);

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    VideoResponse searchAfterDate(@RequestParam(value="key") String key,
                         @RequestParam(value="channelId") String channelId,
                         @RequestParam(value="part") String part,
                         @RequestParam(value="publishedAfter") String publishedAfter,
                         @RequestParam(value="type") String type);

    @RequestMapping(method = RequestMethod.GET, value = "/videos")
    VideoDurationResponse getVideoDuration(@RequestParam(value="id") String videoId,
                                           @RequestParam(value="key") String key,
                                           @RequestParam(value="part", defaultValue = "contentDetails") String part);
}
