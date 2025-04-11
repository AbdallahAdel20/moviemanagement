package com.movieapp.moviemanagement.exception;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Getter
public class ErrorDetails {
    private LocalDateTime timestamp;
    private int status;
    private List<String> errors;

    public ErrorDetails(LocalDateTime timestamp, int status, List<String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
    }


//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public List<String> getErrors() {
//        return errors;
//    }
//
//    public void setErrors(List<String> errors) {
//        this.errors = errors;
//    }
}
