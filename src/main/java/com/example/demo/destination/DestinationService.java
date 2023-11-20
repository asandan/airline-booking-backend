package com.example.demo.destination;

import org.springframework.stereotype.Service;

@Service
public class DestinationService {

  private final DestinationRepository destinationRepository;

  public DestinationService(DestinationRepository destinationRepository) {
    this.destinationRepository = destinationRepository;
  }

  public Destination getDestinationByCities(
    Long destinationFrom,
    Long destinationTo
  ) throws Exception {
    try {
      System.out.println(
        "DESTINATION TO: " +
        destinationTo +
        " DESTINATION FROM: " +
        destinationFrom
      );
      return destinationRepository
        .findDestinationByCities(destinationFrom, destinationTo)
        .orElseThrow(() ->
          new IllegalStateException("Destination with cities does not exist")
        );
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
}
