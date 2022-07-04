package com.github.prgrms.social.service.post;

import com.github.prgrms.social.error.NotFoundException;
import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.post.Post;
import com.github.prgrms.social.model.user.User;
import com.github.prgrms.social.repository.post.PostRepository;
import com.github.prgrms.social.repository.post.like.PostLikeRepository;
import com.github.prgrms.social.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class PostService {

  private final UserRepository userRepository;

  private final PostRepository postRepository;

  private final PostLikeRepository postLikeRepository;

  public PostService(UserRepository userRepository, PostRepository postRepository, PostLikeRepository postLikeRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
    this.postLikeRepository = postLikeRepository;
  }

  @Transactional
  public Post write(Post post) {
    return insert(post);
  }

  @Transactional
  public Post modify(Post post) {
    update(post);
    return post;
  }

  @Transactional
  public Optional<Post> like(Id<Post, Long> postId, Id<User, Long> readerId, Id<User, Long> writerId) {
    // TODO PostLikeRepository를 구현하고, 포스트 좋아요 서비스를 구현하세요.
    checkArgument(postId != null, "postId must be provided.");
    checkArgument(readerId != null, "readerId must be provided.");
    checkArgument(writerId != null, "writerId must be provided.");

    return findById(postId, readerId, writerId).map(post -> {
      if (!post.isLikesOfMe()) {
        post.incrementAndGetLikes();
        postLikeRepository.insert(postId, readerId);
        update(post);
      }
      return post;
    });
  }

  @Transactional(readOnly = true)
  public Optional<Post> findById(Id<Post, Long> postId, Id<User, Long> readerId, Id<User, Long> writerId) {
    checkArgument(postId != null, "postId must be provided.");
    checkArgument(readerId != null, "readerId must be provided.");
    checkArgument(writerId != null, "writerId must be provided.");
    // TODO likesOfMe를 효율적으로 구하기 위해 변경 필요
    return postRepository.findById(postId, readerId, writerId);
  }

  @Transactional(readOnly = true)
  public List<Post> findAll(Id<User, Long> writerId, Id<User, Long> readerId, long offset, int limit) {
    checkArgument(writerId != null, "writerId must be provided.");
    checkArgument(readerId != null, "readerId must be provided.");

    userRepository.findById(writerId)
      .orElseThrow(() -> new NotFoundException(User.class, writerId));
    // TODO likesOfMe를 효율적으로 구하기 위해 변경 필요
    return postRepository.findAll(writerId, readerId, checkOffset(offset), checkLimit(limit));
  }

  private long checkOffset(long offset) {
    return offset < 0 ? 0 : offset;
  }

  private int checkLimit(int limit) {
    return limit < 1 ? 5 : limit;
  }

  private Post insert(Post post) {
    return postRepository.insert(post);
  }

  private void update(Post post) {
    postRepository.update(post);
  }

}