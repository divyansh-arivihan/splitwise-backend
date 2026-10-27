package com.splitwise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private int statusCode;

    public static <T> ApiResponse<T> ok(String message, T data) {
        return of(true, message, data, HttpStatus.OK);
    }

    public static <T> ApiResponse<T> ok(String message) {
        return of(true, message, null, HttpStatus.OK);
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return of(true, message, data, HttpStatus.CREATED);
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return of(false, message, null, status);
    }

    public static <T> ApiResponse<T> error(String message, T data, HttpStatus status) {
        return of(false, message, data, status);
    }

    private static <T> ApiResponse<T> of(boolean success, String message, T data, HttpStatus status) {
        return ApiResponse.<T>builder()
                .success(success)
                .message(message)
                .data(data)
                .statusCode(status.value())
                .build();
    }
}
