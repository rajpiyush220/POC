package com.touchblankspot.service.apitypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

  @JsonProperty("streetName")
  private String streetName;

  @JsonProperty("district")
  private String district;

  @JsonProperty("state")
  private String state;

  @JsonProperty("country")
  private String country;

  @JsonProperty("pinCode")
  private String pinCode;

}
