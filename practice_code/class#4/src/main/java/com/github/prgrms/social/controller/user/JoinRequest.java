package com.github.prgrms.social.controller.user;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class JoinRequest {

  @ApiModelProperty(value = "이름", required = true)
  private String name;

  @ApiModelProperty(value = "로그인 이메일", required = true)
  private String principal;

  @ApiModelProperty(value = "로그인 비밀번호", required = true)
  private String credentials;

  protected JoinRequest() {}

<<<<<<< HEAD
  public JoinRequest(String name, String principal, String credentials) {
    this.name = name;
    this.principal = principal;
    this.credentials = credentials;
  }

=======
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
  public String getName() {
    return name;
  }

  public String getPrincipal() {
    return principal;
  }

  public String getCredentials() {
    return credentials;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("name", name)
      .append("principal", principal)
      .append("credentials", credentials)
      .toString();
  }

}