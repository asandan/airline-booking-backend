package com.example.demo.destination;

import com.example.demo.city.City;
import jakarta.persistence.*;

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

    @ManyToOne()
    @JoinColumn(name = "destinationFrom", referencedColumnName = "id")
    private City destinationFrom;

    @ManyToOne()
    @JoinColumn(name = "destinationTo", referencedColumnName = "id")
    private City getDestinationTo;

    private String description;

    public Destination() {}

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
}
