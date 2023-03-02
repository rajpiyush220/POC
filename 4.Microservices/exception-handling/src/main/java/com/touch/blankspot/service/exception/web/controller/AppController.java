package com.touch.blankspot.service.exception.web.controller;

import com.touch.blankspot.service.exception.annotations.ExceptionHandlerRestController;
import com.touch.blankspot.service.exception.common.error.ApiResponse;
import com.touch.blankspot.service.exception.common.error.BusinessException;
import com.touch.blankspot.service.exception.common.error.ExceptionType;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@ExceptionHandlerRestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AppController {

  @GetMapping("/hello")
  public ResponseEntity<ApiResponse<?>> sayHello() {
    return ResponseEntity.ok(
        new ApiResponse(true, HttpStatus.OK, "Hello from app at " + LocalDateTime.now()));
  }

  @GetMapping("/sampleException")
  public ResponseEntity<ApiResponse<BusinessException>> throwException() {
    throw new BusinessException(
        ExceptionType.DATA_MISMATCH, new ApiResponse<>("sample exception", "Sample exception"));
  }
}
