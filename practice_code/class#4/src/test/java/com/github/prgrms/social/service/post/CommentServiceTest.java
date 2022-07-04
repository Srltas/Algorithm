package com.github.prgrms.social.service.post;

<<<<<<< HEAD
import com.github.prgrms.social.controller.post.CommentRequest;
import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.post.Comment;
import com.github.prgrms.social.model.post.Post;
import com.github.prgrms.social.model.post.Writer;
import com.github.prgrms.social.model.user.Email;
import com.github.prgrms.social.model.user.User;
=======
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

<<<<<<< HEAD
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

=======
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceTest {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired private PostService postService;

  @Autowired private CommentService commentService;

<<<<<<< HEAD
  private Id<Post, Long> postId;

  private Id<User, Long> postWriterId;

  private Id<User, Long> userId;

  @BeforeAll
  void setUp() {
    postId = Id.of(Post.class, 1L);
    postWriterId = Id.of(User.class, 1L);
    userId = Id.of(User.class, 2L);
=======
  @BeforeAll
  void setUp() {
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
  }

  @Test
  @Order(1)
  void 코멘트를_작성한다() {
    // TODO 테스트 추가
<<<<<<< HEAD
    Writer writer = new Writer(new Email("test01@gmail.com"), "tester2");
    String contents = randomAlphabetic(10);
    Comment comment = commentService.write(postId, postWriterId, userId, new CommentRequest(contents).newComment(userId, postId, writer));

    assertThat(comment, is(notNullValue()));
    assertThat(comment.getSeq(), is(notNullValue()));
    assertThat(comment.getContents(), is(contents));
    log.info("Written comment: {}", comment);
=======
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
  }

  @Test
  @Order(3)
  void 댓글_목록을_조회한다() {
    // TODO 테스트 추가
<<<<<<< HEAD
    List<Comment> comments = commentService.findAll(postId, postWriterId, userId);
    assertThat(comments, is(notNullValue()));
    assertThat(comments.size(), is(1));
=======
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
  }

}