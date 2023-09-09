package com.example.NymbleTravelPlanner.services;


import com.example.NymbleTravelPlanner.entities.TravelPackage;
import com.example.NymbleTravelPlanner.globalExceptionHandler.TravelPackageExist;
import com.example.NymbleTravelPlanner.globalExceptionHandler.TravelPackageNotFoundException;
import com.example.NymbleTravelPlanner.repositories.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {

    private final TravelPackageRepository travelPackageRepository;

    @Autowired
    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository) {
        this.travelPackageRepository = travelPackageRepository;
    }

    @Override
    public Page<TravelPackage> getAllTravelPackages(Pageable pageable) {
        return travelPackageRepository.findAll(pageable);
    }

    @Override
    public TravelPackage getTravelPackageById(Long id) {
        return travelPackageRepository.findById(id).orElse(null);
    }



    @Override
    public TravelPackage createTravelPackage(TravelPackage travelPackage) {
        Optional<TravelPackage> existingTravelPackage = travelPackageRepository.findBytravelPackageName(travelPackage.getTravelPackageName());

        if (existingTravelPackage.isPresent()) {
            throw new TravelPackageExist("Travel package with name " + travelPackage.getTravelPackageName() + " Exist try other name");
        } else {
            // If no travel package with the same name exists, save the new travel package
            return travelPackageRepository.save(travelPackage);

        }
    }

    @Override
    public TravelPackage updateTravelPackage(Long id, TravelPackage updatedTravelPackage) {
        Optional<TravelPackage> optionalExistingTravelPackage = travelPackageRepository.findById(id);

        if (optionalExistingTravelPackage.isPresent()) {
            TravelPackage existingTravelPackage = setNewTravelPackage(updatedTravelPackage, optionalExistingTravelPackage);
            return travelPackageRepository.save(existingTravelPackage);
        } else {
            // Handle the case where the travel package with the given ID is not found
            throw new TravelPackageNotFoundException("Travel package with ID " + id + " not found");
        }
    }

    private static TravelPackage setNewTravelPackage(TravelPackage updatedTravelPackage, Optional<TravelPackage> optionalExistingTravelPackage) {
        TravelPackage existingTravelPackage = optionalExistingTravelPackage.get();
        // TODO: 07/09/23 can add more validation before saving in db 
        // Update the fields of the existing travel package with the provided data
        existingTravelPackage.setTravelPackageName(updatedTravelPackage.getTravelPackageName());
        existingTravelPackage.setPassengerCapacity(updatedTravelPackage.getPassengerCapacity());
//        if(updatedTravelPackage.getDestination().stream().anyMatch(destination -> destination.getId() == null)){
//            throw new RuntimeException("At least one destination has a null id.");
//        }
//        existingTravelPackage.setDestination(updatedTravelPackage.getDestination());
//        existingTravelPackage.setSeatAvailable(updatedTravelPackage.getSeatAvailable());
        return existingTravelPackage;
    }

    @Override
    public boolean deleteTravelPackage(Long id) {
        TravelPackage existingTravelPackage = travelPackageRepository.findById(id).orElse(null);
        if (existingTravelPackage != null) {
            travelPackageRepository.delete(existingTravelPackage);
            return true;
        }
        return false;
    }
}

