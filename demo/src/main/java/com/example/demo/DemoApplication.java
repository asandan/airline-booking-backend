package com.example.demo;

import com.example.demo.city.City;
import com.example.demo.city.CityRepository;
import com.example.demo.country.Country;
import com.example.demo.country.CountryRepository;
import com.example.demo.destination.Destination;
import com.example.demo.destination.DestinationRepository;
import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketRepository;
import com.github.javafaker.Faker;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private DestinationRepository destinationRepository;

  @Autowired
  private TicketRepository ticketRepository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner() {
    try {
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

        List<City> cities = cityRepository.findAll();

        for (int i = 0; i < cities.size(); i++) {
          for (int j = 0; j < cities.size(); j++) {
            if (i != j) {
              City destinationFrom = cities.get(i);
              City destinationTo = cities.get(j);
              Destination destination = new Destination();
              destination.setDestinationFrom(destinationFrom);
              destination.setDestinationTo(destinationTo);
              destinationRepository.save(destination);
            }
          }
        }

        Long totalDestinations = destinationRepository.count();

        for (int i = 0; i < totalDestinations; i += 2) {
          Ticket ticket = new Ticket();
          System.out.println(destinationRepository.findById((long) i));
          Optional<Destination> destinationOptional = destinationRepository.findById(
            (long) i
          );
          if (destinationOptional.isPresent()) {
            Destination destination = destinationOptional.get();
            ticket.setDestination(destination);
            ticket.setPrice((float) faker.number().randomNumber(3, true));
            ticket.setQuantity(faker.number().numberBetween(1, 5));
            ticketRepository.save(ticket);
          }
        }
      };
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
