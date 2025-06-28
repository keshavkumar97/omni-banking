package org.omni.bank.auth.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author KeshavKumar
 * This DTO class is aBuilder pattern class
 */
@Getter
public class ErrorResponse {
    private final HttpStatus statusCode;
    private final String error;
    private final String message;
    private final String path;

    private ErrorResponse(Builder errorResponseBuilder) {
        this.statusCode = errorResponseBuilder.statusCode;
        this.message = errorResponseBuilder.message;
        this.error = errorResponseBuilder.error;
        this.path = errorResponseBuilder.path;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private HttpStatus statusCode;
        private String error;
        private String message;
        private String path;

        private Builder() {

        }

        public Builder statusCode(HttpStatus code) {
            this.statusCode = code;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder message(String msg) {
            this.message = msg;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}

