package com.example.demo.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/booking")
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
}
