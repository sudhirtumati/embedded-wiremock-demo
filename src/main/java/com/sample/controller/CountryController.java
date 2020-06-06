package com.sample.controller;

import com.sample.repository.ICountryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final ICountryRepository countriesRepository;

    public CountryController(ICountryRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @GetMapping
    public String getCountries() {
        return countriesRepository.getCountries();
    }
}
