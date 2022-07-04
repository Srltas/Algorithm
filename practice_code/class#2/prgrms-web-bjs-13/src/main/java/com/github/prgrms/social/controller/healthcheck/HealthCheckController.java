package com.github.prgrms.social.controller.healthcheck;

import com.github.prgrms.social.controller.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.prgrms.social.controller.ApiResult.OK;

@RestController
@RequestMapping("api")
public class HealthCheckController {
  @GetMapping("_hcheck")
  public ApiResult<Long> healthCheck() {
    return OK(System.currentTimeMillis());
  }
}
