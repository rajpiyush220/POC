package com.touchblankspot.youtube.player.web.type;

import com.touchblankspot.youtube.player.data.model.YoutubeVideoDetail;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YoutubeVideoDetailMapper {

	@Mapping(source = "publishDate", target = "publishDate", dateFormat = "yyyy-MM-dd")
	YoutubeVideoDetailResponse toApi(YoutubeVideoDetail entity);

}
