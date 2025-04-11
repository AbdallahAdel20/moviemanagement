package com.movieapp.moviemanagement.security;

import com.movieapp.moviemanagement.entity.TokenEntity;
import com.movieapp.moviemanagement.entity.UserEntity;
import com.movieapp.moviemanagement.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;


public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getJWTFromRequest(request);
        if (StringUtils.hasText(token)) {
            Optional<TokenEntity> tokenEntityOpt = tokenRepository.findByAccessToken(token);

            if (tokenEntityOpt.isPresent()) {
                TokenEntity tokenEntity = tokenEntityOpt.get();

                if (tokenEntity.getTokenExpiry().isAfter(Instant.now()) && jwtGenerator.validateToken(token)) {
                    UserEntity user = tokenEntity.getUser();
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                    return;
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token not found");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
