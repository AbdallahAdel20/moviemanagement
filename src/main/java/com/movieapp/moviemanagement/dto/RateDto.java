package com.movieapp.moviemanagement.dto;

import lombok.Data;

@Data
public class RateDto {
    private Long Id;
    private Long movieId;
    private Long userId;
    private int rating;
}
