package com.github.prgrms.socialserver.domain.dto;

public class CommonResponseDTO<T> {

    private boolean success;
    private T response;

    protected CommonResponseDTO(boolean success, T response) {
        this.success = success;
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResponse() {
        return response;
    }

    public static <T> Builder<T> builder() {
        return new CommonResponseDTO.Builder();
    }

    public static class Builder<T> {
        private boolean success;
        private T response;

        Builder() {
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder response(T response) {
            this.response = response;
            return this;
        }

        public CommonResponseDTO build() {
            return new CommonResponseDTO(this.success, this.response);
        }
    }
}
