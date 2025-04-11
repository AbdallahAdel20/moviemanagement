package com.movieapp.moviemanagement.controller;

import com.movieapp.moviemanagement.dto.MovieDto;
import com.movieapp.moviemanagement.service.OMDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/omdb")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class OMDBController {

    private final OMDBService omdbService;

    @GetMapping("/search")
    public ResponseEntity<MovieDto> search(@RequestParam String title) {
        return ResponseEntity.ok(omdbService.getMovie(title));
    }
}
