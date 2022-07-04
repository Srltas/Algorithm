package com.github.prgrms.social.controller.post;

import com.github.prgrms.social.configure.support.Pageable;
import com.github.prgrms.social.controller.ApiResult;
import com.github.prgrms.social.error.NotFoundException;
import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.post.Post;
import com.github.prgrms.social.model.post.Writer;
import com.github.prgrms.social.model.user.User;
import com.github.prgrms.social.security.JwtAuthentication;
import com.github.prgrms.social.service.post.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.prgrms.social.controller.ApiResult.OK;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api")
@Api(tags = "포스터 APIs")
public class PostRestController {

  private final PostService postService;

  public PostRestController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping(path = "post")
  @ApiOperation(value = "포스터 등록")
  public ApiResult<PostDto> posting(
    @AuthenticationPrincipal JwtAuthentication authentication,
    @RequestBody PostingRequest request
  ) {
    return OK(
      new PostDto(
        postService.write(
          request.newPost(authentication.id, new Writer(authentication.email))
        )
      )
    );
  }

  @GetMapping(path = "user/{userId}/post/list")
  @ApiOperation(value = "포스터 목록 조회")
  public ApiResult<List<PostDto>> posts(
    @AuthenticationPrincipal JwtAuthentication authentication,
    @PathVariable Long userId,
    Pageable pageable
  ) {
    // TODO query parameter에 offset, limit 파라미터를 추가하고 페이징 처리한다.
    // offset: 페이징 offset, 기본값 0
    // limit: 최대 조회 갯수, 기본값 5

    return OK(
      postService.findAll(Id.of(User.class, userId), authentication.id, pageable.offset(), pageable.limit()).stream()
        .map(PostDto::new)
        .collect(toList())
    );
  }

  @PatchMapping(path = "user/{userId}/post/{postId}/like")
  @ApiOperation(value = "포스터 좋아요")
  public ApiResult<PostDto> like(
    @AuthenticationPrincipal JwtAuthentication authentication,
    @PathVariable Long userId,
    @PathVariable Long postId
  ) {
    return OK(
      postService.like(Id.of(Post.class, postId), authentication.id ,Id.of(User.class, userId))
        .map(PostDto::new)
        .orElseThrow(() -> new NotFoundException(Post.class, postId))
    );
  }

}