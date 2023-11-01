package com.example.demo.ticket;

import com.example.demo.destination.Destination;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket getTicketByDestination(Long destinationId) throws Exception{
        try {
            return ticketRepository.
                    findTicketByDestination(destinationId).
                    orElseThrow(() -> new IllegalStateException(
                                    "Ticket with destinationId " + destinationId
                                            + " does not exist"
                            )
                    );
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
