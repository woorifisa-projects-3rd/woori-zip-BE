package fisa.woorizip.backend.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse<T> {

    private boolean isSuccess;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ApiResponse(boolean isSuccess, T data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public ApiResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public static <T> ApiResponse success(T data) {
        return new ApiResponse(true, data);
    }

    public static ApiResponse success() {
        return new ApiResponse(true);
    }

    public static <T> ApiResponse fail(T data) {
        return new ApiResponse(false, data);
    }
}
