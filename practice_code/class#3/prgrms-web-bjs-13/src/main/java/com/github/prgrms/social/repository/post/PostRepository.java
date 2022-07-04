package com.github.prgrms.social.repository.post;

import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.post.Post;
import com.github.prgrms.social.model.user.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

  Post insert(Post post);

  void update(Post post);

  Optional<Post> findById(Id<Post, Long> postId, Id<User, Long> readerId, Id<User, Long> writerId);

  List<Post> findAll(Id<User, Long> writerId, Id<User, Long> readerId, long offset, int limit);

}