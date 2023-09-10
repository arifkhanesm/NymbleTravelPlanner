package com.example.NymbleTravelPlanner.services;

import com.example.NymbleTravelPlanner.dto.BookingDto;
import com.example.NymbleTravelPlanner.entities.*;
import com.example.NymbleTravelPlanner.globalExceptionHandler.NymbleTravelPlannerCommonException;
import com.example.NymbleTravelPlanner.repositories.PassengerRepository;
import com.example.NymbleTravelPlanner.repositories.TravelPackageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelBookingServiceImplTest {

    @InjectMocks
    private TravelBookingServiceImpl travelBookingService;

    @Mock
    private TravelPackageRepository travelPackageRepository;

    @Mock
    private PassengerRepository passengerRepository;



    @Test
    public void testCreateBooking_SuccessfulBooking() {
        // Create a BookingDto
        BookingDto bookingDto = new BookingDto();
        bookingDto.setTravelPackageId(1L);
        bookingDto.setPassengerId(2L);

        // Create mock objects
        TravelPackage mockTravelPackage = new TravelPackage();
        mockTravelPackage.setSeatAvailable(1);
        mockTravelPackage.setNumberOfPassengerEnrolled(0);

        Passenger mockPassenger = new Passenger();
        mockPassenger.setPassengerType(Passenger.PassengerType.GOLD);
        mockPassenger.setBalance(100.0);

        Destination destination = new Destination();
        Activity activity = new Activity();
        activity.setCost(50.0);
        activity.setCapacity(1);
        destination.setActivities(activity);
        List<Destination> destinations = new ArrayList<>();
        destinations.add(destination);
        mockTravelPackage.setDestinations(destinations);

        when(travelPackageRepository.findById(1L)).thenReturn(Optional.of(mockTravelPackage));
        when(passengerRepository.findById(2L)).thenReturn(Optional.of(mockPassenger));

        // Call the createBooking method
        Passenger resultPassenger = travelBookingService.createBooking(bookingDto);

        // Verify that the booking was successful
        assertEquals(55.0, resultPassenger.getBalance());
        assertEquals(0, mockTravelPackage.getSeatAvailable());
        assertEquals(1, mockTravelPackage.getNumberOfPassengerEnrolled());
        assertEquals(45.0, mockPassenger.getTravelHistory().get(0).getPricePaid()); // Check if discount was applied
    }

    @Test
    public void testCreateBooking_InsufficientBalance() {
        // Create a BookingDto
        BookingDto bookingDto = new BookingDto();
        bookingDto.setTravelPackageId(1L);
        bookingDto.setPassengerId(2L);

        // Create mock objects
        TravelPackage mockTravelPackage = new TravelPackage();
        Destination destination = new Destination();
        Activity activity = new Activity();
        activity.setCost(50.0);
        activity.setCapacity(1);
        destination.setActivities(activity);
        List<Destination> destinations = new ArrayList<>();
        destinations.add(destination);
        mockTravelPackage.setDestinations(destinations);

        Passenger mockPassenger = new Passenger();
        mockPassenger.setBalance(10.0);

        when(travelPackageRepository.findById(1L)).thenReturn(Optional.of(mockTravelPackage));
        when(passengerRepository.findById(2L)).thenReturn(Optional.of(mockPassenger));

        // Call the createBooking method and expect an exception
        assertThrows(NymbleTravelPlannerCommonException.class, () -> travelBookingService.createBooking(bookingDto));
    }

    @Test
    public void testCreateBooking_NoSeatsAvailable() {
        // Create a BookingDto
        BookingDto bookingDto = new BookingDto();
        bookingDto.setTravelPackageId(1L);
        bookingDto.setPassengerId(2L);

        // Create mock objects
        TravelPackage mockTravelPackage = new TravelPackage();
        mockTravelPackage.setSeatAvailable(0);

        when(travelPackageRepository.findById(1L)).thenReturn(Optional.of(mockTravelPackage));

        // Call the createBooking method and expect an exception
        assertThrows(NymbleTravelPlannerCommonException.class, () -> travelBookingService.createBooking(bookingDto));
    }


    @Test
    public void testCreateBooking_DestinationCapacityReached() {
        // Create a BookingDto
        BookingDto bookingDto = new BookingDto();
        bookingDto.setTravelPackageId(1L);
        bookingDto.setPassengerId(2L);

        // Create mock objects
        TravelPackage mockTravelPackage = new TravelPackage();
        mockTravelPackage.setSeatAvailable(1);

        Passenger mockPassenger = new Passenger();
        mockPassenger.setBalance(100.0);

        Destination destination = new Destination();
        Activity activity = new Activity();
        activity.setCost(50.0);
        activity.setCapacity(0); // Capacity reached
        destination.setActivities(activity);
        List<Destination> destinations = new ArrayList<>();
        destinations.add(destination);
        mockTravelPackage.setDestinations(destinations);

        when(travelPackageRepository.findById(1L)).thenReturn(Optional.of(mockTravelPackage));
        when(passengerRepository.findById(2L)).thenReturn(Optional.of(mockPassenger));

        // Call the createBooking method and expect an exception
        assertThrows(NymbleTravelPlannerCommonException.class, () -> travelBookingService.createBooking(bookingDto));
    }
}
