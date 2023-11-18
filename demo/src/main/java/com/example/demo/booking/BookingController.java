package com.example.demo.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/booking")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping()
    public List<Booking> findAll() {
        return this.bookingService.findAll();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Booking> findOne(@PathVariable String id) throws Exception {
        try {
            Booking booking = this.bookingService.findOne(Long.valueOf(id));
            return ResponseEntity.ok(booking);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(path = "book")
    public Object createBooking(@RequestBody BookingRequest booking) {
        try {
            System.out.println(booking.getQuantity() + " " + booking.getTicketId() + " " + booking.getUserId());
            ResponseEntity<String> newBooking = this.bookingService.book(booking);
            return ResponseEntity.ok(newBooking).toString();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(path = "/getByUser/{userId}")
    public ResponseEntity<List<Booking>> getBookingByUser(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(this.bookingService
                    .getBookingByUser(Long.valueOf(userId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
