package com.github.prgrms.social.security;

import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class RequestUrlMatcher {

  @Value("${regex.url-access-check}")
  private String pattern;

  public boolean requiresAuthorization(HttpServletRequest request) {
    return new RegexRequestMatcher(pattern, null).matches(request);
  }

  public Id<User, Long> obtainTargetId(HttpServletRequest request) {
    Matcher matcher = compile(pattern).matcher(request.getRequestURI());

    return matcher.matches() ? Id.of(User.class, Long.parseLong(matcher.group(1))) : Id.of(User.class, -1l);
  }
}
