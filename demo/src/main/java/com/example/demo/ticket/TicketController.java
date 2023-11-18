package com.example.demo.ticket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(path = "/getByDestination")
    public ResponseEntity<Ticket> getTicketByDestination(@RequestBody TicketRequest ticketRequest) {
        try {
            System.out.println(ticketRequest.getDestinationFrom() + " " + ticketRequest.getDestinationTo());
            return ResponseEntity.ok(this.ticketService
                    .getTicketByDestination((Long) ticketRequest.getDestinationFrom(), (Long) ticketRequest.getDestinationTo()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
