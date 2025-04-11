package com.movieapp.moviemanagement.controller;

import com.movieapp.moviemanagement.dto.MovieDto;
import com.movieapp.moviemanagement.response.ApiResponse;
import com.movieapp.moviemanagement.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping("/addMovies")
    public ResponseEntity<List<MovieDto>> addMovies(@RequestBody List<MovieDto> movieDtos) {
        return ResponseEntity.ok(movieService.addMovies(movieDtos));
    }

    @DeleteMapping("/deleteMovies")
    public ResponseEntity<ApiResponse<String>> deleteMovies(@RequestBody List<Long> movieIds) {
        movieService.deleteMovies(movieIds);
        return ResponseEntity.ok(new ApiResponse<>(true, null, "Movies deleted successfully"));
    }
}
