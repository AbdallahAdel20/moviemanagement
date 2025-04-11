package com.movieapp.moviemanagement.repository;

import com.movieapp.moviemanagement.entity.TokenEntity;
import com.movieapp.moviemanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByAccessToken(String accessToken);

    @Transactional
    void deleteByUser(UserEntity user);
}
