package com.github.prgrms.social.security;

import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.user.User;
import com.github.prgrms.social.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.ClassUtils.isAssignable;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public class ConnectionBasedVoter implements AccessDecisionVoter<FilterInvocation> {

  private final RequestUrlMatcher requestUrlMatcher;
  private UserService userService;

  public ConnectionBasedVoter(RequestUrlMatcher requestUrlMatcher) {
    checkArgument(isNotEmpty(requestUrlMatcher), "requestUrlMatcher must be provided.");
    this.requestUrlMatcher = requestUrlMatcher;
  }

  @Override
  public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
    HttpServletRequest request = fi.getRequest();
    // TODO 접근 대상 리소스가 본인 또는 친구관계인지 확인하고 접근 혀용/거절 처리 구현

    if (!requestUrlMatcher.requiresAuthorization(request)) {
      return ACCESS_GRANTED;
    }

    if (!JwtAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
      return ACCESS_ABSTAIN;
    }

    JwtAuthentication jwtAuth = (JwtAuthentication) authentication.getPrincipal();
    Id<User, Long> targetId = requestUrlMatcher.obtainTargetId(request);

    if (jwtAuth.id.equals(targetId)) {
      return ACCESS_GRANTED;
    }

    List<Id<User, Long>> connectedIds = userService.findConnectedIds(jwtAuth.id);
    if (connectedIds.contains(targetId)) {
      return ACCESS_GRANTED;
    }

    return ACCESS_DENIED;
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return isAssignable(FilterInvocation.class, clazz);
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}