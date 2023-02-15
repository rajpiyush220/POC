package com.touch.blankspot.oauth.integration.fiegn.client;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "fusionAuthFeignClient", url = "localhost:9011")
public interface FusionAuthFeignClient {

  @GetMapping(value = "/oauth2/logout?client_id={clientId}")
  public void logout(@Param String clientId);
}
