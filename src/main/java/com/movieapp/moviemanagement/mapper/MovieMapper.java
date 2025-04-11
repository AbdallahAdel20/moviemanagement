package com.movieapp.moviemanagement.mapper;

import com.movieapp.moviemanagement.dto.MovieDto;
import com.movieapp.moviemanagement.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto toDto(Movie movie);

    Movie toEntity(MovieDto movieDto);
}
