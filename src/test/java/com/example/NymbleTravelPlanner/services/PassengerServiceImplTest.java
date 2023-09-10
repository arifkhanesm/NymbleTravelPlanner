package com.example.NymbleTravelPlanner.services;

import com.example.NymbleTravelPlanner.entities.Passenger;
import com.example.NymbleTravelPlanner.entities.TravelHistory;
import com.example.NymbleTravelPlanner.globalExceptionHandler.NymbleTravelPlannerCommonException;
import com.example.NymbleTravelPlanner.repositories.PassengerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PassengerServiceImplTest {
@InjectMocks
    private PassengerServiceImpl passengerService;
@Mock
private PassengerRepository passengerRepository;

    void setUp() {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>());
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(obj()));
        when(passengerRepository.existsById(1L)).thenReturn(true);
    }
    Passenger obj(){
       Passenger passenger=new Passenger();
        passenger.setId(1L);
        passenger.setName("Test Passenger");
        passenger.setPassengerNumber("+918388388383");
        passenger.setPassengerType(Passenger.PassengerType.GOLD);
        return passenger;
    }

    @Test
    void getAllPassengers() {
        // Mock behavior of repository methods
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>());

        List<Passenger> passengers = passengerService.getAllPassengers();
        assertNotNull(passengers);
        assertEquals(0, passengers.size());
    }

    @Test
    void getPassengerById() {
        Mockito.when(passengerRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(obj()));
        Optional<Passenger> passenger = passengerService.getPassengerById(1L);
        assertTrue(passenger.isPresent());
        assertEquals(obj(), passenger.get());
    }

    @Test
    void createPassenger() {
      //  Mockito.when()
        Passenger newPassenger = new Passenger();
        newPassenger.setName("New Passenger");

        when(passengerRepository.save(newPassenger)).thenReturn(newPassenger);

        Passenger createdPassenger = passengerService.createPassenger(newPassenger);
        assertNotNull(createdPassenger);
        assertEquals(newPassenger, createdPassenger);
    }

    @Test
    void createPassengerWithTravelHistory() {
        Passenger passengerWithHistory = new Passenger();
        passengerWithHistory.setName("Passenger with History");
        passengerWithHistory.getTravelHistory().add(new TravelHistory());

        assertThrows(NymbleTravelPlannerCommonException.class, () -> {
            passengerService.createPassenger(passengerWithHistory);
        });
    }

    @Test
    void updatePassenger() {
        Mockito.when(passengerRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Passenger updatedPassenger = new Passenger();
        updatedPassenger.setName("Updated Passenger");

        when(passengerRepository.save(updatedPassenger)).thenReturn(updatedPassenger);

        Passenger result = passengerService.updatePassenger(1L, updatedPassenger);
        assertEquals(updatedPassenger, result);
    }
    @Test
    void updatePassengerNagativeCase() {
        Mockito.when(passengerRepository.existsById(Mockito.anyLong())).thenReturn(false);
        assertThrows(NymbleTravelPlannerCommonException.class, () -> {
            passengerService.updatePassenger(1L, obj());
        });
    }


    @Test
    void deletePassenger() {
        assertDoesNotThrow(() -> {
            passengerService.deletePassenger(1L);
        });
    }

}