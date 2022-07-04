package com.github.prgrms.social.api.controller.notification;

import com.github.prgrms.social.api.controller.ApiResult;
import com.github.prgrms.social.api.model.notification.Subscription;
import com.github.prgrms.social.api.security.JwtAuthentication;
import com.github.prgrms.social.api.service.notification.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.github.prgrms.social.api.controller.ApiResult.OK;

@RestController
@RequestMapping("api")
@Api(tags = "Push 구독 APIs")
public class SubscribeController {

  private final NotificationService notificationService;

  public SubscribeController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PostMapping("/subscribe")
  @ApiOperation(value = "Push 구독")
  public ApiResult<Subscription> subscribe(
    @AuthenticationPrincipal JwtAuthentication authentication,
    @RequestBody Subscription subscription
  ) {
    Subscription.SubscriptionBuilder subscriptionBuilder = new Subscription.SubscriptionBuilder(subscription);
    subscriptionBuilder.userId(authentication.id);
    subscriptionBuilder.createAt(LocalDateTime.now());

    Subscription subscribe = notificationService.subscribe(subscriptionBuilder.build());
    return OK(subscribe);
  }

}