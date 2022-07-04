package com.github.prgrms.social.controller.post;

import com.github.prgrms.social.model.post.Comment;
import com.github.prgrms.social.model.post.Writer;
<<<<<<< HEAD
import io.swagger.annotations.ApiModelProperty;
=======
>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class CommentDto {

<<<<<<< HEAD
  @ApiModelProperty(value = "PK", required = true)
  private Long seq;

  @ApiModelProperty(value = "내용", required = true)
  private String contents;

  @ApiModelProperty(value = "작성자")
  private Writer writer;

  @ApiModelProperty(value = "작성일시", required = true)
=======
  private Long seq;

  private String contents;

  private Writer writer;

>>>>>>> 08efee8c4c42d8f21fbf98a0f4014c784ecd4685
  private LocalDateTime createAt;

  public CommentDto(Comment source) {
    copyProperties(source, this);

    this.writer = source.getWriter().orElse(null);
  }

  public Long getSeq() {
    return seq;
  }

  public void setSeq(Long seq) {
    this.seq = seq;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public Writer getWriter() {
    return writer;
  }

  public void setWriter(Writer writer) {
    this.writer = writer;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("seq", seq)
      .append("contents", contents)
      .append("writer", writer)
      .append("createAt", createAt)
      .toString();
  }

}