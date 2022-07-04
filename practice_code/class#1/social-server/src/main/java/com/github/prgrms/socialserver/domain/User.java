package com.github.prgrms.socialserver.domain;

import java.time.LocalDateTime;

public class User {

    private Long seq;
    private String email;
    private String password;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createAt;

    protected User(Long seq, String email, String password, int loginCount, LocalDateTime lastLoginAt, LocalDateTime createAt) {
        this.seq = seq;
        this.email = email;
        this.password = password;
        this.loginCount = loginCount;
        this.lastLoginAt = lastLoginAt;
        this.createAt = createAt;
    }

    public Long getSeq() {
        return seq;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
        return new User.Builder();
    }

    public static class Builder {
        private Long seq;
        private String email;
        private String password;
        private int loginCount;
        private LocalDateTime lastLoginAt;
        private LocalDateTime createAt;

        Builder(){
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
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

        public User build() {
            return new User(this.seq, this.email, this.password, this.loginCount, this.lastLoginAt, this.createAt);
        }
    }
}
