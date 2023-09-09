package com.example.NymbleTravelPlanner.services;

import com.example.NymbleTravelPlanner.dto.BookingDto;
import com.example.NymbleTravelPlanner.entities.Passenger;

public interface TravelBookingService  {
    Passenger createBooking(BookingDto reqBookingDto);
}
