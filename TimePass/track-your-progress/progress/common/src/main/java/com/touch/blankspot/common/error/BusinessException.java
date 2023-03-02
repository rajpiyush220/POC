package com.touch.blankspot.common.error;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class BusinessException extends RuntimeException {

  private final ExceptionType exceptionType;

  private List<FieldValidationError> validationErrors;

  private List<ApiErrorDetail> errorDetail;

  private ApiResponse<?> apiResponse;

  private String table;

  private String internalNotes;

  public BusinessException(ExceptionType exceptionType, Throwable cause) {
    super(exceptionType.toString());
    this.exceptionType = exceptionType;
    initCause(cause);
  }

  public BusinessException(ExceptionType exceptionType, ApiResponse<?> apiResponse) {
    super(exceptionType.toString());
    this.exceptionType = exceptionType;
    this.apiResponse = apiResponse;
  }

  public BusinessException(ExceptionType exceptionType, String message) {
    super(message != null ? message : exceptionType.toString());
    this.exceptionType = exceptionType;
  }

  public BusinessException(ExceptionType exceptionType, String message, Throwable cause) {
    this(exceptionType, message);
    initCause(cause);
  }

  public BusinessException(ExceptionType exceptionType, ErrorCode errorCode) {
    super(exceptionType.toString());
    this.exceptionType = exceptionType;
    errorDetail = new ArrayList<>();
    errorDetail.add(new ApiErrorDetail(errorCode.name(), null, null, null));
  }

  public BusinessException(
      ExceptionType exceptionType,
      String message,
      ErrorCode errorCode,
      String detailMessage,
      Throwable cause) {
    this(exceptionType, message, errorCode, detailMessage);
    initCause(cause);
  }

  public BusinessException(
      ExceptionType exceptionType, String message, ErrorCode errorCode, String detailMessage) {
    super(message != null ? message : exceptionType.toString());
    this.exceptionType = exceptionType;
    detailMessage = detailMessage != null ? detailMessage : errorCode.toString();
    errorDetail = new ArrayList<>();
    errorDetail.add(new ApiErrorDetail(errorCode.name(), null, null, null));
  }

  public BusinessException(
      ExceptionType exceptionType,
      String message,
      List<ApiErrorDetail> errorDetail,
      Throwable cause) {
    this(exceptionType, message, errorDetail);
    initCause(cause);
  }

  public BusinessException(
      ExceptionType exceptionType, String message, List<ApiErrorDetail> errorDetail) {
    super(message != null ? message : exceptionType.toString());
    this.exceptionType = exceptionType;
    this.errorDetail = errorDetail;
  }

  public BusinessException(List<FieldValidationError> validationErrors) {
    super("validation error");
    this.exceptionType = ExceptionType.INVALID_INPUT;
    this.validationErrors = validationErrors;
  }
}
