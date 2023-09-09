package com.example.NymbleTravelPlanner.repositories;

import com.example.NymbleTravelPlanner.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
