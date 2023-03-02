package com.touch.blankspot;

import com.sun.istack.NotNull;
import com.touch.blankspot.common.annotation.ProgressPortalRestController;
import com.touch.blankspot.common.error.ApiError;
import com.touch.blankspot.common.error.ApiError.ApiErrorBuilder;
import com.touch.blankspot.common.error.ApiErrorDetail;
import com.touch.blankspot.common.error.ApiResponse;
import com.touch.blankspot.common.error.BusinessException;
import com.touch.blankspot.common.error.ExceptionType;
import com.touch.blankspot.common.service.MessageService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(annotations = {ProgressPortalRestController.class})
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class GlobalExceptionHandler {

  @NotNull private final MessageService messageService;

  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ExceptionHandler(BusinessException.class)
  public @ResponseBody ResponseEntity<ApiResponse<Void>> handleBusinessException(
          BusinessException ex, HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {
    log.error(ex.getMessage(), ex);

    ApiErrorBuilder apiErrorBuilder = getApiErrorBuilder(ex.getExceptionType(), ex.getMessage());
    String message = ex.getMessage();

    if (StringUtils.isBlank(message) || message.equals(ex.getExceptionType().name())) {
      message = messageService.getMessage(ex.getExceptionType().name());
    }

    if (ex.getErrorDetail() != null) {
      for (ApiErrorDetail apiErrorDetail : ex.getErrorDetail()) {
        if (StringUtils.isBlank(apiErrorDetail.getMessage())
            || apiErrorDetail.getMessage().equals(apiErrorDetail.getCode())) {
          apiErrorDetail.setMessage(messageService.getMessage(apiErrorDetail.getCode()));
        }
      }
      apiErrorBuilder.details(ex.getErrorDetail());
    }

    if (ex.getValidationErrors() != null) {

      List<ApiErrorDetail> fieldValidationErrors =
          ex.getValidationErrors().stream()
              .map(
                  fieldValidationError ->
                      ApiErrorDetail.builder()
                          .rejectedValue(fieldValidationError.getRejectedValue())
                          .field(fieldValidationError.getFieldName())
                          .message(fieldValidationError.getMessage())
                          .build())
              .toList();
      fieldValidationErrors.addAll(apiErrorBuilder.build().getDetails());
      apiErrorBuilder.details(fieldValidationErrors);
    }
    ApiResponse<Void> apiResponse =
        new ApiResponse<>(
            apiErrorBuilder.code(ex.getExceptionType().name()).message(message).build());

    // add log writing
    return ResponseEntity.ok().body(apiResponse);
  }

  private ApiErrorBuilder getApiErrorBuilder(ExceptionType exceptionType, String message) {
    ApiErrorBuilder builder;

    if (StringUtils.isBlank(message)) {
      message = messageService.getMessage(exceptionType.name());
    }
    builder = ApiError.builder().code(exceptionType.name()).message(message);
    return builder;
  }
}
