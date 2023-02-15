package com.touchblankspot.service.inventory;

import com.netflix.discovery.EurekaClient;
import com.touchblankspot.common.error.ApiResponse;
import com.touchblankspot.service.inventory.web.annotations.InventoryRestController;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@InventoryRestController
@RequiredArgsConstructor
public class SampleController {

  @NonNull
  private final EurekaClient eurekaClient;

  @Value("${spring.application.name}")
  private String appName;

  @GetMapping("/hello")
  public ApiResponse<String> testController(@RequestParam("exception") String exception) {
    System.err.println("Sample request");
    String response =
        String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    return new ApiResponse<>(response, "Welcome Message");
  }
}
