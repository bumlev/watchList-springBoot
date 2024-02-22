package com.openclassrooms.watchlist.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MovieRatingService {

    String apiUrl = "https://www.omdbapi.com/?apikey=30eb0fb7&t=";

    public String getMovieRating(String title) {

        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + title, ObjectNode.class);
            ObjectNode jsonObject = response.getBody();
            return jsonObject.path("imdbRating").asText();
        } catch (Exception e) {
            System.out.println("Something went wront while calling OMDB API " + e.getMessage());
            return null;
        }
    }
}
