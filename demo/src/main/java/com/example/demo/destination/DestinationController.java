package com.example.demo.destination;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/destination")
public class DestinationController {
    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/getByCities")
    public Destination getDestinationByCities(
             @RequestBody String destinationTo
            , @RequestBody String destinationFrom) throws Exception {
        Long destinationToLong = Long.valueOf(destinationTo);
        Long destinationFromLong = Long.valueOf(destinationFrom);
        return this.destinationService.getDestinationByCities(destinationToLong, destinationFromLong);
    }

}
