package com.example.demo.booking;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketRepository;
import com.example.demo.transaction.Transaction;
import com.example.demo.transaction.TransactionRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

  private final BookingRepository bookingRepository;

  private final TicketRepository ticketRepository;

  private final UserRepository userRepository;

  private final TransactionRepository transactionRepository;

  public BookingService(
    BookingRepository bookingRepository,
    TicketRepository ticketRepository,
    UserRepository userRepository,
    TransactionRepository transactionRepository
  ) {
    this.bookingRepository = bookingRepository;
    this.ticketRepository = ticketRepository;
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
  }

  public List<Booking> findAll() {
    return bookingRepository.findAll();
  }

  public Booking findOne(Long id) throws Exception {
    try {
      return bookingRepository
        .findById(id)
        .orElseThrow(() ->
          new IllegalStateException("Booking with id " + id + " does not exist")
        );
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  @Transactional
  public ResponseEntity<String> book(BookingRequest bookingRequest)
    throws Exception {
    try {
      Long userId = bookingRequest.getUserId();
      Long ticketId = bookingRequest.getTicketId();
      int quantity = bookingRequest.getQuantity();

      Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

      if (ticketOptional.isEmpty()) {
        return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body("Ticket not found");
      }
      Ticket ticket = ticketOptional.get();

      if (ticket.getQuantity() < quantity) {
        return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Not enough tickets");
      }
      Optional<User> userOptional = userRepository.findById(userId);

      if (userOptional.isEmpty()) {
        return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body("User not found");
      }

      User user = userOptional.get();

      if (user.getBalance() >= ticket.getPrice() * quantity) {
        Long newBalance = (long) (
          user.getBalance() - ticket.getPrice() * quantity
        );
        user.setBalance(newBalance);
        userRepository.save(user);
      } else {
        return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Not enough balance");
      }

      Booking booking = new Booking();
      Transaction transaction = new Transaction();

      booking.setUser(user);
      booking.setTicket(ticket);
      booking.setQuantity(quantity);

      Booking savedBooking = bookingRepository.save(booking);

      transaction.setUser(user);
      transaction.setBooking(savedBooking);
      transaction.setPrice(ticket.getPrice() * quantity);

      transactionRepository.save(transaction);

      ticket.setQuantity(ticket.getQuantity() - quantity);

      ticketRepository.save(ticket);

      return ResponseEntity.ok(savedBooking.toString());
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
