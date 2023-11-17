package com.example.demo.country;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/country")
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    @GetMapping()
    public ResponseEntity<List<Country>> getCountries() {
        try {
            return ResponseEntity.ok(countryRepository.findCountries());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
