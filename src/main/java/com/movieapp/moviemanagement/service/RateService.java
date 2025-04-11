package com.movieapp.moviemanagement.service;

import com.movieapp.moviemanagement.entity.Rate;
import com.movieapp.moviemanagement.entity.UserEntity;
import com.movieapp.moviemanagement.mapper.RateMapper;
import com.movieapp.moviemanagement.repository.MovieRepository;
import com.movieapp.moviemanagement.repository.RateRepository;
import com.movieapp.moviemanagement.repository.UserRepository;
import com.movieapp.moviemanagement.request.RateRequest;
import com.movieapp.moviemanagement.response.RateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateRepository rateRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final RateMapper rateMapper;

    public String addRate(RateRequest rateRequest){
        try {
            UserEntity user = userRepository.findByUsername(getUsername()).get();
            Rate rate = Rate.builder()
                    .rating(rateRequest.getRating())
                    .movie(movieRepository.findById(Long.valueOf(rateRequest.getMovieId())).get())
                    .user(user)
                    .build();
            rateRepository.save(rate);
            return "Rate added successfully";
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<RateResponse> getRatesByMovieId(Long movieId){
        List<Rate> rates = rateRepository.findByMovieId(movieId);
        return rates.stream().map(rateMapper::toResponse).toList();
    }

    private String getUsername() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }





}
