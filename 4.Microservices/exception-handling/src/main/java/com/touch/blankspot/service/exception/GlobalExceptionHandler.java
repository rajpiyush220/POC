package com.touch.blankspot.service.exception;

import com.touch.blankspot.service.exception.common.error.ApiResponse;
import com.touch.blankspot.service.exception.common.error.BusinessException;
import com.touch.blankspot.service.exception.common.error.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  protected ResponseEntity<ApiResponse<?>> handleGlobalException(BusinessException exception) {
    ExceptionType exceptionType = exception.getExceptionType();
    HttpStatusCode statusCode =
        exceptionType.getStatus() != null ? exceptionType.getStatus() : HttpStatus.BAD_REQUEST;
    ApiResponse<?> result =
        exception.getApiResponse() != null ? exception.getApiResponse() : new ApiResponse<>();

    if (exception.getValidationErrors() != null && !exception.getValidationErrors().isEmpty()) {
      statusCode = HttpStatus.BAD_REQUEST;
      result = new ApiResponse<>(exception.getValidationErrors(), exceptionType.getDescription());
    }
    return ResponseEntity.status(statusCode).body(result);
  }

  @ExceptionHandler({Exception.class})
  protected ResponseEntity<ApiResponse<?>> handleException(Exception exception) {
    return ResponseEntity.badRequest()
        .body(ApiResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getMessage()));
  }
}
