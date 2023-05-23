package com.touchblankspot.keycloak.auth.converter;

import lombok.Builder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;

import java.util.Collections;
import java.util.Map;

@Builder
public class CustomClaimConverter implements
        Converter<Map<String, Object>, Map<String, Object>> {

    private final MappedJwtClaimSetConverter delegate =
            MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

    public Map<String, Object> convert(Map<String, Object> claims) {
        Map<String, Object> convertedClaims = this.delegate.convert(claims);
        String zipcode = convertedClaims.get("zipcode") != null ? convertedClaims.get("zipcode").toString() : "N/A";
        String dob = convertedClaims.get("dob") != null ? convertedClaims.get("dob").toString() : "N/A";

        convertedClaims.put("zipcode", zipcode);
        convertedClaims.put("dob",dob);

        return convertedClaims;
    }
}