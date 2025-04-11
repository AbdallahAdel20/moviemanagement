package com.movieapp.moviemanagement.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.moviemanagement.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class OMDBService {

    public MovieDto getMovie(String title) {
        MovieDto movieDto = new MovieDto();
        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode rootNode = mapper.readTree(title);
//            String actualTitle = rootNode.get("title").asText();
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://www.omdbapi.com/?t=" + encodedTitle + "&apikey=6fa8f747"))
                    .build();


            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                movieDto = objectMapper.readValue(response.body(), MovieDto.class);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching movie details", e);
        }
        return movieDto;
    }
}
