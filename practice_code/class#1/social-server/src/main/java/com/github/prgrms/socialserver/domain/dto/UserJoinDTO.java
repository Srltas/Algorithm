package com.github.prgrms.socialserver.domain.dto;

public class UserJoinDTO {

    private String principal;
    private String credentials;

    protected UserJoinDTO(String principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getCredentials() {
        return credentials;
    }

    public static Builder builder() {
        return new UserJoinDTO.Builder();
    }

    public static class Builder {
        private String principal;
        private String credentials;

        Builder(){
        }

        public Builder principal(String principal) {
            this.principal = principal;
            return this;
        }

        public Builder credentials(String credentials) {
            this.credentials = credentials;
            return this;
        }

        public UserJoinDTO build() {
            return new UserJoinDTO(this.principal, this.credentials);
        }
    }
}
