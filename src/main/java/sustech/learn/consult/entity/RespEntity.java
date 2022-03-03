package sustech.learn.consult.entity;

import org.springframework.http.HttpStatus;

public class RespEntity {
    private Integer code;
    private String message;
    private Object data;

    public static Builder code(Integer code) {
        Builder builder = new Builder();
        builder.entity.code = code;
        return builder;
    }

    public static Builder code(HttpStatus status) {
        return code(status.value());
    }

    public static RespEntity ok() {
        return code(200).message("success").build();
    }

    public static RespEntity ok(Object body) {
        return code(200).message("success").body(body).build();
    }

    public static RespEntity error(String message) {
        return code(400).message(message).build();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static class Builder {
        private RespEntity entity;

        public Builder() {
            this.entity = new RespEntity();
        }

        public Builder message(String message) {
            this.entity.message = message;
            return this;
        }

        public Builder body(Object data) {
            this.entity.data = data;
            return this;
        }

        public RespEntity build() {
            return this.entity;
        }
    }
}
