package com.sample.repository;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Component
@Profile("demo")
public class CountryDemoRepository implements ICountryRepository {

    private final String url;
    private final RestTemplate restTemplate;

    @PostConstruct
    void initialize() {
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(9099).usingFilesUnderClasspath("wiremock"));
        wireMockServer.start();
    }

    private CountryDemoRepository(RestTemplate restTemplate, @Value("${external.api.url}") String url) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public String getCountries() {
        return restTemplate.getForObject(url, String.class);
    }
}
