package com.example.demo.booking;

import com.example.demo.ticket.Ticket;
import com.example.demo.transaction.Transaction;
import com.example.demo.user.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @SequenceGenerator(
            name = "destination_sequence",
            sequenceName = "destination_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "destination_sequence"
    )
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "ticketId", referencedColumnName = "id")
    private Ticket ticket;

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "transactionId", referencedColumnName = "id")
    private Transaction transaction;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    private int quantity;

    public Booking() {}

    public Booking(int quantity) {
        this.quantity = quantity;
    }

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

    public Long getUserId() {
        return user.getId();
    }

    public void setUserId(Long userId) {
        this.user.setId(userId);
    }

    public Long getTicketId() {
        return ticket.getId();
    }

    public void setTicketId(Long ticketId) {
        this.ticket.setId(ticketId);
    }

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }





}
