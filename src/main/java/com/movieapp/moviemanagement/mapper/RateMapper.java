package com.movieapp.moviemanagement.mapper;

import com.movieapp.moviemanagement.dto.RateDto;
import com.movieapp.moviemanagement.entity.Rate;
import com.movieapp.moviemanagement.response.RateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RateMapper {

    @Mapping(target = "movieId", source = "movie.id")
    @Mapping(target = "userId", source = "user.id")
    RateDto toDto(Rate rate);

    Rate toEntity(RateDto rateDto);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "rating" , source = "rating")
    RateResponse toResponse(Rate rate);
}
