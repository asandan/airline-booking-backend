package com.example.demo.booking;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public BookingService(UserRepository userRepository, BookingRepository bookingRepository, TicketRepository ticketRepository){
        this.bookingRepository = bookingRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
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
            Optional<User> userOptional = userRepository.findById(userId);

            if(userOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            User user = userOptional.get();

            if(user.getBalance() >= ticket.getPrice() * quantity){
                Long newBalance = (long) (user.getBalance() - ticket.getPrice() * quantity);
                user.setBalance(newBalance);
                userRepository.save(user);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough balance");
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
}