package com.example.demo.city;

import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final CityRepository cityRepository;
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City findOne(Long countryId) throws Exception {
        try {
            return cityRepository.
                    findCitiesByCountry(countryId).
                    orElseThrow(() -> new IllegalStateException(
                                    "City with countryId " + countryId
                                            + " does not exist"
                            )
                    );
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
