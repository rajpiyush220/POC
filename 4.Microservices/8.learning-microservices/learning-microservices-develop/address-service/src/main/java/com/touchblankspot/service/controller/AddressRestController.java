package com.touchblankspot.service.controller;

import com.touchblankspot.service.apitypes.AddressResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AddressRestController {

  @GetMapping("/address/get/details")
  public ResponseEntity<List<AddressResponse>> getUserAddress() {
    log.info("Inside getUserAddress");
    List<AddressResponse> addressResponses =
        List.of(
            new AddressResponse("Street 1", "District 1", "State 1", "India", "560076"),
            new AddressResponse("Street 2", "District 2", "State 2", "India", "560077"));
    System.err.println("Sample request");
    return ResponseEntity.ok(addressResponses);
  }

}
