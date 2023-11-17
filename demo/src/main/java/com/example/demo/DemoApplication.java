package com.example.demo;

import com.example.demo.city.City;
import com.example.demo.city.CityRepository;
import com.example.demo.country.Country;
import com.example.demo.country.CountryRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;

@SpringBootApplication
@RestController
public class DemoApplication {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            int maxCountries = 5; // Adjust the number of countries as needed
            int maxCitiesPerCountry = 3; // Adjust the number of cities per country as needed
            Faker faker = new Faker(new Locale("en-US"));

            for (int i = 0; i < maxCountries; i++) {
                Country country = new Country(faker.address().country());
                countryRepository.save(country);

                for (int j = 0; j < maxCitiesPerCountry; j++) {
                    City city = new City(faker.address().city());
                    city.setCountry(country);
                    cityRepository.save(city);
                }

            }
        };
    }
    }



