package com.example.demo.ticket;

import com.example.demo.city.City;
import com.example.demo.destination.Destination;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

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

  @ManyToOne
  @JoinColumn(name = "destinationId", referencedColumnName = "id")
  private Destination destination;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  }

  public void setDestination(Destination destination) {
    this.destination = destination;
  }

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

  public Ticket orElseThrow(
    Supplier<? extends RuntimeException> exceptionSupplier
  ) {
    return Optional.of(this).orElseThrow(exceptionSupplier);
  }

  public Long getDestination() {
    return this.destination.getId();
  }
}
