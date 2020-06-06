package com.sample.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("!demo")
public class CountryRepository implements ICountryRepository {

    private final String url;
    private final RestTemplate restTemplate;

    public CountryRepository(RestTemplate restTemplate, @Value("${external.api.url}") String url) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public String getCountries() {
        return restTemplate.getForObject(url, String.class);
    }
}
