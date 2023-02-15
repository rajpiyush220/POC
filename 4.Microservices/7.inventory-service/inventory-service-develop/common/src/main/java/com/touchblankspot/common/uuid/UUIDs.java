package com.touchblankspot.common.uuid;

import java.util.UUID;
import lombok.NonNull;
import org.springframework.util.ObjectUtils;

public class UUIDs {

  public static UUID attemptParseUUID(String uuidCandidate) {
    if (uuidCandidate == null || ObjectUtils.isEmpty(uuidCandidate)) {
      return null;
    }
    try {
      return parseUUID(uuidCandidate);
    } catch (IllegalArgumentException e) {

    }
    return null;
  }

  public static UUID parseUUID(@NonNull String uuidString) {
    if (uuidString.length() != 36
        || uuidString.charAt(8) != '-'
        || uuidString.charAt(13) != '-'
        || uuidString.charAt(18) != '-'
        || uuidString.charAt(23) != '-') {
      throw new IllegalArgumentException("Unable to parse UUID " + uuidString);
    }
    UUID uuid = UUID.fromString(uuidString);
    if (!uuidString.equalsIgnoreCase(uuid.toString())) {
      throw new IllegalArgumentException("Invalid UUID " + uuidString);
    }
    return uuid;
  }
}
