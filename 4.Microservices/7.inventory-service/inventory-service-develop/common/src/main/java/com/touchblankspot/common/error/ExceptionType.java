package com.touchblankspot.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionType {
  DATA_MISMATCH(HttpStatus.BAD_REQUEST, "Input parameters don't match each other."),
  INVALID_INPUT(HttpStatus.BAD_REQUEST, "Input parameter is not correct"),
  INVALID_FIELD_VALUE(
      HttpStatus.BAD_REQUEST, "Parameter passed for field is not meeting its required format."),
  PERMISSION_DENIED(HttpStatus.FORBIDDEN, "Forbidden"),

  SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error Occurred"),

  VALIDATION_ERROR(
      HttpStatus.BAD_REQUEST, "Request Parameter did not pass their validation, see error details"),
  REQUEST_METHOD_NOT_SUPPORTED(
      HttpStatus.METHOD_NOT_ALLOWED, "HTTP method provided is not supported on this endpoint");

  private final HttpStatus status;

  private final String description;
}
