package com.example.demo.ticket;

import com.example.demo.destination.DestinationRepository;
import com.example.demo.destination.DestinationService;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

  private final TicketRepository ticketRepository;
  private final DestinationService destinationService;

  public TicketService(
    TicketRepository ticketRepository,
    DestinationRepository destinationRepository
  ) {
    this.ticketRepository = ticketRepository;
    this.destinationService = new DestinationService(destinationRepository);
  }

  public Ticket getTicketByDestination(
    Long destinationFrom,
    Long destinationTo
  ) throws Exception {
    try {
      return ticketRepository
        .findTicketByDestinationId(
          destinationService
            .getDestinationByCities(destinationFrom, destinationTo)
            .getId()
        )
        .orElseThrow(() ->
          new IllegalStateException("Ticket with destination does not exist")
        );
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
}
