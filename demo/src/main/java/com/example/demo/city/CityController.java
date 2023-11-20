package com.example.demo.city;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/city")
public class CityController {

  private final CityService cityService;

  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @GetMapping(path = "byCountry/{id}")
  public ResponseEntity<List<City>> findAllCitiesByCountry(
    @PathVariable String id
  ) {
    try {
      return ResponseEntity.ok(
        this.cityService.findAllByCountry(Long.valueOf(id))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }
}
