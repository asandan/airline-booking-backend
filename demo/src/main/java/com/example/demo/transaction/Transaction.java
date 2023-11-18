package com.example.demo.transaction;

import com.example.demo.booking.Booking;
import com.example.demo.user.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    private Long id;

    @OneToOne()
    @JoinColumn(name = "bookingId", referencedColumnName = "id")
    private Booking booking;

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    private float price;

    public Transaction() {}

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBookingId(Long bookingId){
        this.booking.setId(bookingId);
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(Long userId){
        this.user.setId(userId);
    }

    public Long getUserId(){
        return this.user.getId();
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
