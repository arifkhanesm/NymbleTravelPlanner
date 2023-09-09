package com.example.NymbleTravelPlanner.controller;

import com.example.NymbleTravelPlanner.entities.Destination;
import com.example.NymbleTravelPlanner.entities.Passenger;
import com.example.NymbleTravelPlanner.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/destination")
public class DetinationController {
    @Autowired
    DestinationRepository destinationRepository;
    @GetMapping
    public ResponseEntity<List<Destination>> getAllPassengers() {
        List<Destination> destination = destinationRepository.findAll();
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }
}
