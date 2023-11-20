package com.example.demo.city;

import com.example.demo.transaction.Transaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
  @Query("SELECT c FROM City c WHERE c.country.id = ?1")
  Optional<List<City>> findCitiesByCountry(Long countryId);
}
