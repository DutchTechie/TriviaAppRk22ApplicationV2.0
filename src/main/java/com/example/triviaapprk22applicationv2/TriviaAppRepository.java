package com.example.triviaapprk22applicationv2;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class TriviaAppRepository {
    private RestTemplate restTemplate;
    private Class responseType;

    public TriviaAppRepository(RestTemplate restTemplate, Class responseType) {
        this.restTemplate = restTemplate;
        this.responseType = responseType;
    }

    public Object fetch(String uri) {
        return restTemplate.getForObject(uri, responseType);
    }
}
