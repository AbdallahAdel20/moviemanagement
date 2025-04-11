package com.movieapp.moviemanagement.controller;

import com.movieapp.moviemanagement.request.RateRequest;
import com.movieapp.moviemanagement.response.ApiResponse;
import com.movieapp.moviemanagement.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rate")
@RequiredArgsConstructor
public class RateController {
    private final RateService rateService;

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getAllRatesByMovieId(@PathVariable Long movieId) {
        return ResponseEntity.ok(rateService.getRatesByMovieId(movieId));
    }

    @PostMapping("/addRate")
    public ResponseEntity<ApiResponse<String>> addRate(@RequestBody RateRequest rateRequest) {
        return ResponseEntity.ok(new ApiResponse<>(true, rateService.addRate(rateRequest), "Rate added successfully"));

    }
}
