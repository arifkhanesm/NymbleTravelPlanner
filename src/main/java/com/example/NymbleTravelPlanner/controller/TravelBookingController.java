package com.example.NymbleTravelPlanner.controller;

import com.example.NymbleTravelPlanner.dto.BookingDto;
import com.example.NymbleTravelPlanner.entities.Passenger;
import com.example.NymbleTravelPlanner.services.TravelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
public class TravelBookingController {

private  final TravelBookingService travelBookingService;
    @Autowired
    public TravelBookingController(TravelBookingService travelBookingService) {
        this.travelBookingService = travelBookingService;
    }

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody BookingDto reqBookingDto) {
               Passenger createdBooking = travelBookingService.createBooking(reqBookingDto);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }
}
