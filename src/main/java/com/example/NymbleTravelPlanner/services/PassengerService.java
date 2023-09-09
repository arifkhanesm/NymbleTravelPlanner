package com.example.NymbleTravelPlanner.services;

import com.example.NymbleTravelPlanner.entities.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    List<Passenger> getAllPassengers();
    Optional<Passenger> getPassengerById(Long id);
    Passenger createPassenger(Passenger passenger);
    Passenger updatePassenger(Long id, Passenger updatedPassenger);
    void deletePassenger(Long id);
}
