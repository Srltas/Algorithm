package com.github.prgrms.social.configure.support;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static com.google.common.base.Preconditions.checkArgument;

public class PageableRequest implements Pageable {

  private final long offset;

  private final int limit;

  public PageableRequest() {
    this(0,5);
  }

  public PageableRequest(long offset, int limit) {
    checkArgument(offset >= 0 , "offset must be bigger than or equal to 0");
    checkArgument(limit >= 1 , "limit must be bigger than or equal to 1");

    this.offset = offset;
    this.limit = limit;
  }

  @Override
  public long offset() {
    return offset;
  }

  @Override
  public int limit() {
    return limit;
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("offset", offset)
            .append("limit", limit)
            .toString();
  }

}
