package com.github.prgrms.social.repository.post.like;

import com.github.prgrms.social.model.commons.Id;
import com.github.prgrms.social.model.post.Post;
import com.github.prgrms.social.model.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class JdbcPostLikeRepository implements PostLikeRepository{

  private final JdbcTemplate jdbcTemplate;

  public JdbcPostLikeRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void insert(Id<Post, Long> postId,Id<User, Long> readerId) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(conn -> {
      PreparedStatement ps = conn.prepareStatement("INSERT INTO likes(seq,user_seq,post_seq) VALUES (null,?,?)", new String[]{"seq"});
      ps.setLong(1, readerId.value());
      ps.setLong(2, postId.value());
      return ps;
    }, keyHolder);

  }
}
