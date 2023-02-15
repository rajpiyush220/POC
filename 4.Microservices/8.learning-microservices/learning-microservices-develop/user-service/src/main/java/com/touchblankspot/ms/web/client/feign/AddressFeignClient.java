package com.touchblankspot.ms.web.client.feign;

import com.touchblankspot.ms.apitypes.AddressResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "api-gateway")
public interface AddressFeignClient {

  @GetMapping("/address-service/address/get/details")
  ResponseEntity<List<AddressResponse>> getAddress();

}
