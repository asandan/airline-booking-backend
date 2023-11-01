package com.example.demo.city;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<City> findOne(@PathVariable String id) throws Exception {
        try {
            return ResponseEntity.ok(this.cityService.findOne(Long.valueOf(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
