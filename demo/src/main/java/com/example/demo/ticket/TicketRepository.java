package com.example.demo.ticket;

import com.example.demo.destination.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
  @Query("SELECT t FROM Ticket t WHERE t.destination.id=?1")
  Ticket findTicketByDestinationId(Long destinationId);
}
