package com.touchblankspot.ms.apitypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("dateOfBirth")
  private String dateOfBirth;

  @JsonProperty("address")
  private Set<AddressResponse> addressResponses;

}


