package com.example.demo.destination;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/destination")
public class DestinationController {

  private final DestinationRepository destinationRepository;

  public DestinationController(
    DestinationService destinationService,
    DestinationRepository destinationRepository
  ) {
    this.destinationRepository = destinationRepository;
  }

  @PostMapping("/getByCities")
  public Destination getDestinationByCities(
    @RequestBody DestinationQuery query
  ) throws Exception {
    Long destinationFromLong = Long.valueOf(query.getDestinationFrom());
    Long destinationToLong = Long.valueOf(query.getDestinationTo());
    System.out.println(destinationFromLong + " " + destinationToLong);
    return destinationRepository.findDestinationByCities(
      destinationFromLong,
      destinationToLong
    );
  }
}
