package com.touchblankspot.ms.web.controller;

import com.touchblankspot.ms.apitypes.UserResponse;
import com.touchblankspot.ms.web.client.feign.AddressFeignClient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

  @NonNull
  private final AddressFeignClient addressFeignClient;

  @GetMapping("/user/get/details")
  public ResponseEntity<UserResponse> getUserDetails() {
    log.info("Inside getUserDetails");
    UserResponse userResponse = UserResponse.builder().id(UUID.randomUUID()).name("Test")
        .dateOfBirth(LocalDate.now().minusDays(400).toString())
        .addressResponses(
            new HashSet<>(Objects.requireNonNull(addressFeignClient.getAddress().getBody()))
            /*
            Set.of(
                new AddressResponse("Street 1", "District 1", "State 1", "India", "560076"),
                new AddressResponse("Street 2", "District 2", "State 2", "India", "560077")) */)
        .build();
    System.err.println("Sample request");
    return ResponseEntity.ok(userResponse);
  }
}
