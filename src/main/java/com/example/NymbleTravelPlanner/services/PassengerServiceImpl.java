package com.example.NymbleTravelPlanner.services;

import com.example.NymbleTravelPlanner.entities.Passenger;
import com.example.NymbleTravelPlanner.globalExceptionHandler.NymbleTravelPlannerCommonException;
import com.example.NymbleTravelPlanner.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    @Override
    public Passenger createPassenger(Passenger reqPassenger) {
        if(reqPassenger.getTravelHistory().isEmpty()){
        return passengerRepository.save(reqPassenger);}
        else {
            throw new NymbleTravelPlannerCommonException("travel history can not be created while creating passenger");
        }
    }

    /**
     * Update Passenger if it finds id in db
     * @param id
     * @param updatedPassenger
     * @return
     */
    @Override
    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {
        if (passengerRepository.existsById(id)) {
            updatedPassenger.setId(id);
            return passengerRepository.save(updatedPassenger);
        }
        else throw new NymbleTravelPlannerCommonException("User does not exist");
    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}

