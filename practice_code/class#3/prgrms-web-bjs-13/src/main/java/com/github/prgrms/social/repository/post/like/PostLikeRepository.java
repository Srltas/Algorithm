package com.github.prgrms.social.repository.post.like;

import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.post.Post;
import com.github.prgrms.social.model.user.User;

public interface PostLikeRepository {
  void insert(Id<Post, Long> postId, Id<User, Long> readerId);
}
