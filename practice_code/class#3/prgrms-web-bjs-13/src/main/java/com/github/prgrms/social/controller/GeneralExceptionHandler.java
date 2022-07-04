package com.github.prgrms.social.controller;

import com.github.prgrms.social.error.NotFoundException;
import com.github.prgrms.social.error.ServiceRuntimeException;
import com.github.prgrms.social.error.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.github.prgrms.social.controller.ApiResult.ERROR;

@ControllerAdvice
public class GeneralExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private ResponseEntity<ApiResult<?>> newResponse(Throwable throwable, HttpStatus status) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    return new ResponseEntity<>(ERROR(throwable, status), headers, status);
  }

  // TODO REST API 처리 중 발생한 예외를 catch 하고, 로그를 남기고, ApiResult를 사용해 오류 응답을 전달
  @ExceptionHandler({
          IllegalStateException.class,
          IllegalArgumentException.class,
          TypeMismatchException.class,
          MissingServletRequestParameterException.class,
          JSONException.class
  })
  protected ResponseEntity<ApiResult<?>> handleBadRequestException(Exception e) {
    log.debug("Bad request exception occurred: {}", e.getMessage(), e);
    return newResponse(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMediaTypeException.class)
  protected ResponseEntity<ApiResult<?>> handleUnsupportedMediaTypeException(HttpMediaTypeException e) {
    return newResponse(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ApiResult<?>> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e) {
    return newResponse(e, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(ServiceRuntimeException.class)
  public ResponseEntity<ApiResult<?>> handleServiceRuntimeException(ServiceRuntimeException e) {
    if (e instanceof NotFoundException) {
      return newResponse(e, HttpStatus.NOT_FOUND);
    }
    if (e instanceof UnauthorizedException) {
      return newResponse(e, HttpStatus.UNAUTHORIZED);
    }

    log.warn("Unexpected service exception occurred: {}", e.getMessage(), e);
    return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ApiResult<?>> handleInternalServerError(Exception e) {
    log.error("Unexpected exception occurred: {}", e.getMessage(), e);
    return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}