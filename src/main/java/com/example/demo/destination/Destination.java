package com.example.demo.destination;

import com.example.demo.city.City;
import jakarta.persistence.*;
import java.util.Optional;
import java.util.function.Supplier;

@Entity
@Table(name = "destinations")
public class Destination {

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

  @ManyToOne
  @JoinColumn(name = "destinationFromId", referencedColumnName = "id")
  private City destinationFrom;

  @ManyToOne
  @JoinColumn(name = "destinationToId", referencedColumnName = "id")
  private City destinationTo;

  private String description;

  public Destination() {}

  public void setDestinationFrom(City destinationFrom) {
    this.destinationFrom = destinationFrom;
  }

  public void setDestinationTo(City destinationTo) {
    this.destinationTo = destinationTo;
  }

  public Destination(String description) {
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Destination orElseThrow(
    Supplier<? extends RuntimeException> exceptionSupplier
  ) {
    return Optional.of(this).orElseThrow(exceptionSupplier);
  }
}
