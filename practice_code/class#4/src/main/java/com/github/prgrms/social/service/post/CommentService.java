package com.github.prgrms.social.service.post;

<<<<<<< HEAD
import com.github.prgrms.social.error.NotFoundException;
=======
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.post.Comment;
import com.github.prgrms.social.model.post.Post;
import com.github.prgrms.social.model.user.User;
import com.github.prgrms.social.repository.post.CommentRepository;
import com.github.prgrms.social.repository.post.PostRepository;
<<<<<<< HEAD
=======
import org.apache.commons.lang3.NotImplementedException;
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
<<<<<<< HEAD
import static java.util.Collections.emptyList;
=======
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685

@Service
public class CommentService {

  private final PostRepository postRepository;

  private final CommentRepository commentRepository;

  public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
  }

  @Transactional
  public Comment write(Id<Post, Long> postId, Id<User, Long> postWriterId, Id<User, Long> userId, Comment comment) {
    // TODO post의 comments를 1증가시키고, comment를 저장한다. (단, findPost메소드를 활용해 java stream api를 최대한 활용해 fluent 스타일로 구현)
<<<<<<< HEAD
    checkArgument(postId != null, "postId must be provided.");
    checkArgument(postWriterId != null, "postWriterId must be provided.");
    checkArgument(userId != null, "userId must be provided.");
    checkArgument(comment.isNotContentsEmpty(), "comment must be provided.");

    return findPost(postId, postWriterId, userId).map(post -> {
      post.incrementAndGetComments();
      postRepository.update(post);
      return insert(comment);
    }).orElseThrow(() -> new NotFoundException(Post.class, postId));
=======
    throw new NotImplementedException("구현이 필요합니다.");
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
  }

  @Transactional(readOnly = true)
  public List<Comment> findAll(Id<Post, Long> postId, Id<User, Long> postWriterId, Id<User, Long> userId) {
    // TODO 주어진 Post의 comment 목록을 조회 (페이징 필요없음. findPost메소드를 활용해 java stream api를 최대한 활용해 fluent 스타일로 구현)
<<<<<<< HEAD
    checkArgument(postId != null, "postId must be provided.");
    checkArgument(postWriterId != null, "postWriterId must be provided.");
    checkArgument(userId != null, "userId must be provided.");

    return findPost(postId, postWriterId, userId).map(post -> {
      return commentRepository.findAll(postId);
    }).orElse(emptyList());
=======
    throw new NotImplementedException("구현이 필요합니다.");
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
  }

  private Optional<Post> findPost(Id<Post, Long> postId, Id<User, Long> postWriterId, Id<User, Long> userId) {
    checkArgument(postId != null, "postId must be provided.");
    checkArgument(postWriterId != null, "postWriterId must be provided.");
    checkArgument(userId != null, "userId must be provided.");

    return postRepository.findById(postId, postWriterId, userId);
  }

  private Comment insert(Comment comment) {
    return commentRepository.insert(comment);
  }

  private void update(Comment comment) {
    commentRepository.update(comment);
  }

}