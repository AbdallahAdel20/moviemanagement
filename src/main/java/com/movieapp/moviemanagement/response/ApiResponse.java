package com.movieapp.moviemanagement.response;


import com.movieapp.moviemanagement.exception.ErrorDetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class ApiResponse<T> {
    private boolean success;
    private ErrorDetails error;
    private T data;
    private String message;

    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.error = null;
    }

    public ApiResponse(boolean success, ErrorDetails error, String message) {
        this.success = success;
        this.error = error;
        this.message = message;
        this.data = null;
    }

}
