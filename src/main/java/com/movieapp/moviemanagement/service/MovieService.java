package com.movieapp.moviemanagement.service;

import com.movieapp.moviemanagement.dto.MovieDto;
import com.movieapp.moviemanagement.mapper.MovieMapper;
import com.movieapp.moviemanagement.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(movieMapper::toDto).toList();
    }

    public List<MovieDto> addMovies(List<MovieDto> movieDtos) {
        return movieRepository.saveAll(movieDtos.stream().map(movieMapper::toEntity).toList())
                .stream().map(movieMapper::toDto)
                .toList();
    }

    public void deleteMovies(List<Long> movieIds) {
        movieRepository.deleteAllById(movieIds);
    }

    public MovieDto getMovieById(Long id) {
        return movieMapper.toDto(movieRepository.findById(id).orElse(null));
    }
}
