package com.movieapp.moviemanagement.repository;

import com.movieapp.moviemanagement.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("SELECT r FROM Rate r WHERE r.movie.id = :movieId")
    List<Rate> findByMovieId(Long movieId);
}
