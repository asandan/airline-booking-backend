package com.example.demo.booking;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;

    public BookingService(BookingRepository bookingRepository, TicketRepository ticketRepository) {
        this.bookingRepository = bookingRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking findOne(Long id) throws Exception {
        try {
            return bookingRepository.findById(id).orElseThrow(() -> new IllegalStateException("Booking with id " + id + " does not exist"));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<String> book(BookingRequest bookingRequest) throws Exception {
        try {
            Long userId = bookingRequest.getUserId();
            Long ticketId = bookingRequest.getTicketId();
            int quantity = bookingRequest.getQuantity();

            Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

            if (ticketOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
            }
            Ticket ticket = ticketOptional.get();

            if (ticket.getQuantity() < quantity) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough tickets");
            }

            Booking booking = new Booking();

            booking.setUserId(userId);
            booking.setTicketId(ticketId);
            booking.setQuantity(quantity);

            ticket.setQuantity(ticket.getQuantity() - quantity);

            ticketRepository.save(ticket);

            return ResponseEntity.ok(bookingRepository.save(booking).toString());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Booking> getBookingByUser(Long userId) throws Exception {
        try {
            return bookingRepository.findBookingByUserId(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}