package com.example.demo.country;

import com.example.demo.city.City;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

  @Id
  @SequenceGenerator(
    name = "country_sequence",
    sequenceName = "country_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "country_sequence"
  )
  private Long id;

  @Column(unique = true)
  private String name;

  @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
  private List<City> cities;

  public Country() {}

  public Country(String name) {
    this.name = name;
  }

  public Country(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<City> getCities() {
    return cities;
  }

  public void addCity(City city) {
    cities.add(city);
    city.setCountry(this);
  }
}
