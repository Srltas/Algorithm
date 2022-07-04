package com.github.prgrms.social.controller;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

public class ApiError {

  @ApiModelProperty(value = "Error 메세지", required = true)
  private final String message;

  @ApiModelProperty(value = "Error 상태코드", required = true)
  private final int status;

  ApiError(Throwable throwable, HttpStatus status) {
    this(throwable.getMessage(), status);
  }

  ApiError(String message, HttpStatus status) {
    this.message = message;
    this.status = status.value();
  }

  public String getMessage() {
    return message;
  }

  public int getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("message", message)
      .append("status", status)
      .toString();
  }

}