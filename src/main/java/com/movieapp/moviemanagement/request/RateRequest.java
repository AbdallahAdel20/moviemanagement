package com.movieapp.moviemanagement.request;

import lombok.Data;

@Data
public class RateRequest {
    private Integer movieId;
    private Integer rating;
}
