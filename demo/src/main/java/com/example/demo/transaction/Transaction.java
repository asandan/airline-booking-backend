package com.example.demo.transaction;

import com.example.demo.booking.Booking;
import com.example.demo.user.User;
import jakarta.persistence.*;
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @OneToOne()
    @JoinColumn(name = "bookingId", referencedColumnName = "id")
    private Booking booking;

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    private float price;

    public Transaction() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Transaction(Booking booking, User user, float price) {
        this.booking = booking;
        this.user = user;
        this.price = price;
    }




}
