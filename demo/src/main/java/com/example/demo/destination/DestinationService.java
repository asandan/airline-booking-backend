package com.example.demo.destination;

import org.springframework.stereotype.Service;

@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination getDestinationByCities(
            Long destinationTo
            , Long destinationFrom) throws Exception{
        try {
            return destinationRepository.
                    findDestinationByCities(destinationTo, destinationFrom).
                    orElseThrow(() -> new IllegalStateException(
                                    "Destination with cities does not exist"
                            )
                    );
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
