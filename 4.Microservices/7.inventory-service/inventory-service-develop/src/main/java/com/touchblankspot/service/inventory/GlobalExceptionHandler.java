package com.touchblankspot.service.inventory;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.touchblankspot.common.error.ApiError;
import com.touchblankspot.common.error.ApiErrorDetail;
import com.touchblankspot.common.error.ApiResponse;
import com.touchblankspot.common.error.BusinessException;
import com.touchblankspot.common.error.ErrorCode;
import com.touchblankspot.common.error.ExceptionType;
import com.touchblankspot.common.uuid.UuidConverter;
import com.touchblankspot.service.inventory.service.MessageService;
import com.touchblankspot.service.inventory.web.annotations.InventoryRestController;
import com.touchblankspot.service.inventory.web.annotations.ProductRestController;
import com.touchblankspot.service.inventory.web.annotations.SalesRestController;
import com.touchblankspot.service.inventory.web.annotations.StockRestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(
    annotations = {
      InventoryRestController.class,
      ProductRestController.class,
      SalesRestController.class,
      StockRestController.class
    })
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class GlobalExceptionHandler {

  @NonNull private final MessageService messageService;

  @InitBinder
  void registerEditors(WebDataBinder webDataBinder) {
    webDataBinder.registerCustomEditor(UUID.class, new UuidConverter());
  }

  @ExceptionHandler(value = {AccessDeniedException.class})
  public @ResponseBody ResponseEntity<ApiResponse<Void>> handleAccessDeniedException(
      HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
      throws BusinessException {

    log.error(ex.getMessage(), ex);

    ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.FORBIDDEN);
    ApiResponse<Void> apiResponse =
        new ApiResponse<>(
            ApiError.builder()
                .code(ExceptionType.PERMISSION_DENIED.name())
                .message(ex.getMessage())
                .build());
    return builder.body(apiResponse);
  }

  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ExceptionHandler(BusinessException.class)
  public @ResponseBody ResponseEntity<ApiResponse<Void>> handleBusinessException(
      BusinessException ex, HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {

    log.error(ex.getMessage(), ex);

    // Just send generic error details for plaid webhooks
    if (request.getRequestURI().startsWith("/webhook/plaid/event")) {
      // loggingService.logResponse(request, response, null, LogLevel.ERROR);
      return ResponseEntity.status(INTERNAL_SERVER_ERROR)
          .body(
              ApiResponse.failure(
                  ExceptionType.SYSTEM_ERROR.name(), ExceptionType.SYSTEM_ERROR.getDescription()));
    }

    ApiError.ApiErrorBuilder apiErrorBuilder =
        getApiErrorBuilder(ex.getExceptionType(), ex.getMessage());

    String message = ex.getMessage();

    // if message is empty or same as error type, set the message from error.properties
    if (StringUtils.isBlank(message) || message.equals(ex.getExceptionType().name())) {
      message = messageService.getMessage(ex.getExceptionType().name());
    }

    if (ex.getErrorDetail() != null) {

      // if error detail message is null, set the message from error.properties
      for (ApiErrorDetail apiErrorDetail : ex.getErrorDetail()) {
        if (StringUtils.isBlank(apiErrorDetail.getMessage())
            || apiErrorDetail.getMessage().equals(apiErrorDetail.getCode())) {
          apiErrorDetail.setMessage(messageService.getMessage(apiErrorDetail.getCode()));
        }
      }

      apiErrorBuilder.details(ex.getErrorDetail());
    }

    if (ex.getValidationErrors() != null) {
      // Extend the api error details list with all field validation errors.
      List<ApiErrorDetail> fieldValidationErrors =
          ex.getValidationErrors().stream()
              .map(
                  fieldValidationError ->
                      ApiErrorDetail.builder()
                          .rejectedValue(fieldValidationError.getRejectedValue())
                          .field(fieldValidationError.getFieldName())
                          .message(fieldValidationError.getMessage())
                          .build())
              .collect(Collectors.toList());
      fieldValidationErrors.addAll(apiErrorBuilder.build().getDetails());
      apiErrorBuilder.details(fieldValidationErrors);
    }

    ApiResponse<Void> apiResponse =
        new ApiResponse<>(
            apiErrorBuilder.code(ex.getExceptionType().name()).message(message).build());

    // loggingService.logResponse(request, response, apiResponse, LogLevel.ERROR);

    ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
    return builder.body(apiResponse);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(BAD_REQUEST)
  public @ResponseBody ApiResponse<Void> handleMethodArgumentException(
      MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {

    log.error(ex.getMessage(), ex);

    ApiError.ApiErrorBuilder builder = getApiErrorBuilder(ExceptionType.INVALID_FIELD_VALUE, null);

    if (ex.getBindingResult().getAllErrors().size() > 0) {
      List<ApiErrorDetail> details = new ArrayList<>();
      for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
        if (objectError instanceof FieldError fe) {
          ApiErrorDetail apiError =
              new ApiErrorDetail(
                  fe.getCode(), fe.getField(), fe.getDefaultMessage(), fe.getRejectedValue());

          details.add(apiError);
          continue;
        }
        ApiErrorDetail apiError =
            new ApiErrorDetail(
                objectError.getCode(),
                objectError.getObjectName(),
                objectError.getDefaultMessage(),
                null);
        details.add(apiError);
      }
      builder.details(details);
    }
    return new ApiResponse<>(builder.build());
  }

  @ExceptionHandler({MissingRequestHeaderException.class})
  @ResponseStatus(BAD_REQUEST)
  public @ResponseBody ApiResponse<Void> handleMissingRequestHeaderException(
      MissingRequestHeaderException ex, HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {

    log.error(ex.getMessage(), ex);

    ApiError.ApiErrorBuilder builder = getApiErrorBuilder(ExceptionType.INVALID_FIELD_VALUE, null);

    List<ApiErrorDetail> details = new ArrayList<>();

    ApiErrorDetail apiError =
        new ApiErrorDetail(
            ExceptionType.INVALID_FIELD_VALUE.name(), ex.getHeaderName(), ex.getMessage(), null);

    details.add(apiError);
    builder.details(details);
    return new ApiResponse<>(builder.build());
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public @ResponseBody ApiResponse<Void> handleConstraintViolationException(
      ConstraintViolationException ex, HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {

    log.error(ex.getMessage(), ex);

    ApiError.ApiErrorBuilder builder = getApiErrorBuilder(ExceptionType.INVALID_FIELD_VALUE, null);

    if (ex.getConstraintViolations().size() > 0) {
      List<ApiErrorDetail> details = new ArrayList<>();
      for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {

        // [0]:Filed, [1]:Message
        String[] messages = constraintViolation.getMessage().split(":");

        ApiErrorDetail apiError =
            new ApiErrorDetail(
                ExceptionType.INVALID_FIELD_VALUE.name(), messages[0], messages[1], null);

        details.add(apiError);
      }
      builder.details(details);
    }

    return new ApiResponse<>(builder.build());
  }

  @ExceptionHandler({HttpMessageConversionException.class})
  @ResponseStatus(BAD_REQUEST)
  public @ResponseBody ApiResponse<Void> handleHttpMessageConversionException(
      HttpMessageConversionException ex, HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {

    log.error(ex.getMessage(), ex);

    ApiError.ApiErrorBuilder builder = getApiErrorBuilder(ExceptionType.VALIDATION_ERROR, null);
    List<ApiErrorDetail> details = new ArrayList<>();
    String code = ErrorCode.INVALID_REQUEST_BODY_FORMAT.name();
    String detailErrorMessage =
        messageService.getMessage(ErrorCode.INVALID_REQUEST_BODY_FORMAT.toString());
    ApiErrorDetail apiError = new ApiErrorDetail(code, null, detailErrorMessage, null);
    details.add(apiError);
    builder.details(details);

    return new ApiResponse<>(builder.build());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public @ResponseBody ApiResponse<Void> handleMethodNotSupportException(
      Exception ex, HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {

    log.error(ex.getMessage(), ex);

    ApiError.ApiErrorBuilder builder =
        getApiErrorBuilder(ExceptionType.REQUEST_METHOD_NOT_SUPPORTED, null);

    return new ApiResponse<>(builder.build());
  }

  @ExceptionHandler({
    HttpMessageNotReadableException.class,
    MissingServletRequestParameterException.class,
    IllegalArgumentException.class
  })
  @ResponseStatus(BAD_REQUEST)
  public @ResponseBody ApiResponse<Void> handleInvalidFormatException(
      Exception ex, HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {

    log.error(ex.getMessage(), ex);

    ApiError.ApiErrorBuilder builder = getApiErrorBuilder(ExceptionType.INVALID_FIELD_VALUE, null);

    List<ApiErrorDetail> details = new ArrayList<>();
    if (ex.getCause() instanceof InvalidFormatException) {
      String field = ((InvalidFormatException) ex.getCause()).getPath().get(0).getFieldName();

      // Don't pass message as exception information, as its exposing internals publicly.
      ApiErrorDetail apiError =
          new ApiErrorDetail(ExceptionType.INVALID_FIELD_VALUE.name(), field, "", null);
      details.add(apiError);

    } else if (ex instanceof MissingServletRequestParameterException) {
      String field = ((MissingServletRequestParameterException) ex).getParameterName();

      // Don't pass message as exception information, as its exposing internals publicly.
      ApiErrorDetail apiError =
          new ApiErrorDetail(ExceptionType.INVALID_FIELD_VALUE.name(), field, "", null);
      details.add(apiError);
    } else if (ex.getCause() instanceof MismatchedInputException mie) {
      ApiErrorDetail apiError =
          new ApiErrorDetail(
              ExceptionType.INVALID_FIELD_VALUE.name(),
              mie.getPath().stream().map(Reference::getFieldName).collect(Collectors.joining(".")),
              "",
              null);
      details.add(apiError);
    } else {
      ex.getCause();
    }
    builder.details(details);

    return new ApiResponse<>(builder.build());
  }

  @Bean
  public ErrorAttributes defaultErrorAttributes() {
    return new DefaultErrorAttributes() {
      @Override
      public Map<String, Object> getErrorAttributes(
          WebRequest webRequest, ErrorAttributeOptions options) {
        return Map.of(
            "success",
            false,
            "error",
            getApiErrorBuilder(ExceptionType.SYSTEM_ERROR, null).build());
      }
    };
  }

  private ApiError.ApiErrorBuilder getApiErrorBuilder(ExceptionType exceptionType, String message) {
    ApiError.ApiErrorBuilder builder;
    if (StringUtils.isBlank(message)) {
      messageService.getMessage(exceptionType.name());
    }
    builder = ApiError.builder().code(exceptionType.name()).message(message);
    return builder;
  }
}
