package com.example.demo.ticket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(path = "/getByDestination/{destinationId}")
    public ResponseEntity<Ticket> getTicketByDestination(@PathVariable String destinationId) {
        try {
            return ResponseEntity.ok(this.ticketService
                    .getTicketByDestination(Long.valueOf(destinationId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
