package com.example.NymbleTravelPlanner.services;

import com.example.NymbleTravelPlanner.dto.BookingDto;
import com.example.NymbleTravelPlanner.entities.*;
import com.example.NymbleTravelPlanner.globalExceptionHandler.NymbleTravelPlannerCommonException;
import com.example.NymbleTravelPlanner.repositories.PassengerRepository;
import com.example.NymbleTravelPlanner.repositories.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TravelBookingServiceImpl implements TravelBookingService {
    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    private static final double GOLD_DISCOUNT = 0.10;

    @Override
    public Passenger createBooking(BookingDto reqBookingDto) {
        Optional<TravelPackage> travelPackageOpt = travelPackageRepository.findById(reqBookingDto.getTravelPackageId());
        Optional<Passenger> passengerOpt = passengerRepository.findById(reqBookingDto.getPassengerId());

        if (travelPackageOpt.isPresent() && passengerOpt.isPresent() && travelPackageOpt.get().getSeatAvailable() > 0) {
            TravelPackage travelPackage = travelPackageOpt.get();
            Passenger passenger = passengerOpt.get();

            List<TravelHistory> travelHistories = new ArrayList<>();
            double totalPrice = 0;

            for (Destination destination : travelPackage.getDestinations()) {
                Activity activity = destination.getActivities();
                checkDestinationCapacity(activity);

                double activityCost = applyDiscount(passenger.getPassengerType(), activity.getCost());
                totalPrice += activityCost;

                TravelHistory travelHistory = new TravelHistory();
                travelHistory.setActivityName(activity.getName());
                travelHistory.setActualPrice(activity.getCost());
                travelHistory.setPricePaid(activityCost);
                travelHistory.setPackageName(travelPackage.getTravelPackageName());
                travelHistory.setDestinationName(destination.getName());
                travelHistory.setDestinationId(destination.getId());
                travelHistories.add(travelHistory);

                activity.setCapacity(activity.getCapacity() - 1);
            }

            if (totalPrice > passenger.getBalance()) {
                throw new NymbleTravelPlannerCommonException("Your account does not have sufficient balance to book this package");
            }

            passenger.setBalance(passenger.getBalance() - totalPrice);
            passenger.setTravelHistory(travelHistories);

            travelPackage.setSeatAvailable(travelPackage.getSeatAvailable() - 1);
            travelPackage.setNumberOfPassengerEnrolled(travelPackage.getNumberOfPassengerEnrolled() + 1);

            travelPackageRepository.save(travelPackage);
            return passenger;
        } else {
            throw new NymbleTravelPlannerCommonException("It seems no seats are available in this package or passenger not found");
        }
    }

    private void checkDestinationCapacity(Activity activity) {
        if (activity.getCapacity() <= 0) {
            throw new NymbleTravelPlannerCommonException("This " + activity.getName() + " has reached its maximum booking");
        }
    }

    private double applyDiscount(Passenger.PassengerType passengerType, double cost) {
        switch (passengerType) {
            case GOLD:
                return cost - (cost * GOLD_DISCOUNT);
            case STANDARD:
                return cost;
            case PREMIUM:
                return 0; // Premium passengers pay 0 cost
            default:
                throw new NymbleTravelPlannerCommonException("Invalid passenger type");
        }
    }
}
