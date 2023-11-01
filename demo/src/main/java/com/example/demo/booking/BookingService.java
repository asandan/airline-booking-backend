package com.example.demo.booking;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking findOne(Long id) throws Exception {
        try {
            return bookingRepository.
                    findById(id).
                    orElseThrow(() -> new IllegalStateException(
                                    "Booking with id " + id
                                            + " does not exist"
                            )
                    );
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}