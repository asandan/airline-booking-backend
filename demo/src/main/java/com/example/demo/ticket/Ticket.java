package com.example.demo.ticket;

import com.example.demo.destination.Destination;
import jakarta.persistence.*;


@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "destinationId", referencedColumnName = "id")
    private Destination destination;
    private float price;
    private int quantity;

    //create constructors, setters and getters for created fields
    public Ticket() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Ticket(Destination destination, float price, int quantity) {
        this.destination = destination;
        this.price = price;
        this.quantity = quantity;
    }

    public Ticket(Long id, Destination destination, float price, int quantity) {
        this.id = id;
        this.destination = destination;
        this.price = price;
        this.quantity = quantity;
    }
}
