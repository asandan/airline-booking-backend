package com.example.demo.city;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAllByCountry(Long countryId) throws Exception {

        try {
            var cities = cityRepository.
                    findCitiesByCountry(countryId).
                    orElseThrow(() -> new IllegalStateException(
                                    "City with countryId " + countryId
                                            + " does not exist"
                            )
                    );
            System.out.println(cities);
            return cities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}