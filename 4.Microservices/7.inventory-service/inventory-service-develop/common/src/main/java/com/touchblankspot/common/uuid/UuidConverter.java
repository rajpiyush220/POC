package com.touchblankspot.common.uuid;

import java.beans.PropertyEditorSupport;
import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class UuidConverter extends PropertyEditorSupport implements Converter<String, UUID> {

  @Override
  public UUID convert(String id) {
    return UUIDs.parseUUID(id);
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    setValue(StringUtils.hasText(text) ? UUIDs.parseUUID(text.trim()) : null);
  }

  @Override
  public String getAsText() {
    UUID value = (UUID) getValue();
    return value != null ? value.toString() : "";
  }
}
