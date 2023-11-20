package com.example.demo.city;

import com.example.demo.country.Country;
import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class City {

  @Id
  @SequenceGenerator(
    name = "city_sequence",
    sequenceName = "city_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "city_sequence"
  )
  private Long id;

  @Column(unique = true)
  private String name;

  @ManyToOne
  @JoinColumn(name = "countryId", referencedColumnName = "id")
  private Country country;

  public City() {}

  public City(String name) {
    this.name = name;
  }

  public City(Long id, String name, String state, String country) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getName() {
    return name;
  }
}
