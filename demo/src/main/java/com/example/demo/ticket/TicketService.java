package com.example.demo.ticket;

import com.example.demo.destination.Destination;
import com.example.demo.destination.DestinationRepository;
import com.example.demo.destination.DestinationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final DestinationService destinationService;

    public TicketService(TicketRepository ticketRepository, DestinationRepository destinationRepository) {
        this.ticketRepository = ticketRepository;
        this.destinationService = new DestinationService(destinationRepository);
    }

    public Ticket getTicketByDestination(Long destinationFrom, Long destinationTo) throws Exception{
        try {
            return ticketRepository.
                    findTicketByDestinationId(
                            destinationService.
                                    getDestinationByCities(destinationFrom, destinationTo).
                                    getId()
                    ).
                    orElseThrow(() -> new IllegalStateException(
                                    "Ticket with destination does not exist"
                            )
                    );
//            Optional<Destination> foundDestination = Optional.ofNullable(destinationService.getDestinationByCities(destinationFrom, destinationTo));
//            Optional<Ticket>foundtTicket = Optional.ofNullable(ticketRepository.findTicketByDestinationId(foundDestination.get().getId()));
//            System.out.println("FOUND TICKET" + foundDestination.get().getId());
//
//            if(foundtTicket.isEmpty()){
//                throw new Exception("Ticket not found");
//            }
//            return foundtTicket.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
