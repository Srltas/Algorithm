package com.github.prgrms.socialserver.domain.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {

    private Long seq;
    private String principal;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createAt;

    protected UserResponseDTO(Long seq, String principal, int loginCount, LocalDateTime lastLoginAt, LocalDateTime createAt) {
        this.seq = seq;
        this.principal = principal;
        this.loginCount = loginCount;
        this.lastLoginAt = lastLoginAt;
        this.createAt = createAt;
    }

    public Long getSeq() {
        return seq;
    }

    public String getPrincipal() {
        return principal;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public static Builder builder() {
        return new UserResponseDTO.Builder();
    }

    public static class Builder {
        private Long seq;
        private String principal;
        private int loginCount;
        private LocalDateTime lastLoginAt;
        private LocalDateTime createAt;

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder principal(String principal) {
            this.principal = principal;
            return this;
        }

        public Builder loginCount(int loginCount) {
            this.loginCount = loginCount;
            return this;
        }

        public Builder lastLoginAt(LocalDateTime lastLoginAt) {
            this.lastLoginAt = lastLoginAt;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public UserResponseDTO build() {
            return new UserResponseDTO(this.seq, this.principal, this.loginCount, this.lastLoginAt, this.createAt);
        }
    }
}
