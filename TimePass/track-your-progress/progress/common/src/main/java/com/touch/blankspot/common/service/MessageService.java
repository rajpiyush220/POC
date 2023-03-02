package com.touch.blankspot.common.service;

import com.sun.istack.NotNull;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MessageService {

  @NotNull private final MessageSource messageSource;

  public String getMessage(String id) {
    Locale locale = LocaleContextHolder.getLocale();
    try {
      return messageSource.getMessage(id, null, locale);
    } catch (NoSuchMessageException ex) {
      log.warn("Error message of {} is not defined.", id);
      return id;
    }
  }

  public String getMessage(String id, String arg) {
    Locale locale = LocaleContextHolder.getLocale();
    String[] args = new String[] {arg};
    try {
      return messageSource.getMessage(id, args, locale);
    } catch (NoSuchMessageException ex) {
      log.warn("Error message of {} is not defined.", id);
      return id;
    }
  }

  public String getMessage(String id, Object[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    try {
      return messageSource.getMessage(id, args, locale);
    } catch (NoSuchMessageException ex) {
      log.warn("Error message of {} is not defined.", id);
      return id;
    }
  }
}
