package com.example.demo.destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository
  extends JpaRepository<Destination, Long> {
  @Query(
    "SELECT d FROM Destination d WHERE d.destinationFrom.id=?1 AND d.destinationTo.id=?2"
  )
  Destination findDestinationByCities(Long destinationFrom, Long destinationTo);
}
