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
public class TravelBookingServiceImpl implements TravelBookingService{
    @Autowired
    TravelPackageRepository travelPackageRepository;
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public Passenger createBooking(BookingDto reqBookingDto) {
        Optional<TravelPackage> travelPackage= travelPackageRepository.findById(reqBookingDto.getTravelPackageId());
        Optional<Passenger> passenger=passengerRepository.findById(reqBookingDto.getPassengerId());

        if(travelPackage.get().getSeatAvailable()>0){
            travelPackage.get().getDestinations().forEach(
                    it->checkDetinationCapicity(it.getActivities())
            );

           Passenger updatedPassenger=updatePassenger(travelPackage.get(),passenger.get());
           TravelPackage updatedTravelPackage = updateTravelPackage(travelPackage).get();
           travelPackageRepository.save(updatedTravelPackage);
           passengerRepository.save(updatedPassenger);
           return updatedPassenger;
        }
        else throw new NymbleTravelPlannerCommonException("It seems no seats are available in this package try other package");


    }

    private Passenger updatePassenger(TravelPackage travelPackage, Passenger passenger) {
        List<TravelHistory> travelHistories=new ArrayList<>();
        if(passenger.getPassengerType()== Passenger.PassengerType.GOLD){
            double totalPrice=0;

            for(Destination destination:travelPackage.getDestinations()){
                totalPrice=  (destination.getActivities().getCost()-destination.getActivities().getCost()*0.10)+totalPrice;
                TravelHistory travelHistory=new TravelHistory();
                travelHistory.setActivityName(destination.getActivities().getName());
                travelHistory.setActualPrice(destination.getActivities().getCost());
                travelHistory.setPricePaid(destination.getActivities().getCost()-destination.getActivities().getCost()*0.10); //for gold customer 10% discount
                travelHistory.setPackageName(travelPackage.getTravelPackageName());
                travelHistory.setDestinationName(destination.getName());
                travelHistory.setDestinationId(destination.getId());
                travelHistory.setPackageName(travelPackage.getTravelPackageName());
                travelHistories.add(travelHistory);
            }
            if(totalPrice>passenger.getBalance()){
                throw new NymbleTravelPlannerCommonException("Your account doest not have sufficient balance to book this package");
            }
            else passenger.setBalance(passenger.getBalance()-totalPrice);
            passenger.setTravelHistory(travelHistories);
            return passenger;
        }
if(passenger.getPassengerType()== Passenger.PassengerType.STANDARD){
    double totalPrice=0;

    for(Destination destination:travelPackage.getDestinations()){
        totalPrice=  destination.getActivities().getCost()+totalPrice;
        TravelHistory travelHistory=new TravelHistory();
        travelHistory.setActivityName(destination.getActivities().getName());
        travelHistory.setActualPrice(destination.getActivities().getCost());
        travelHistory.setPricePaid(destination.getActivities().getCost()); //for Standard 0% dicount
        travelHistory.setPackageName(travelPackage.getTravelPackageName());
        travelHistory.setDestinationName(destination.getName());
        travelHistory.setDestinationId(destination.getId());
        travelHistory.setPackageName(travelPackage.getTravelPackageName());
        travelHistories.add(travelHistory);
    }
    if(totalPrice>passenger.getBalance()){
        throw new NymbleTravelPlannerCommonException("Your account doest not have sufficient balance to book this travel package");
    }
    else passenger.setBalance(passenger.getBalance()-totalPrice);
    passenger.setTravelHistory(travelHistories);
    return passenger;

}
        if(passenger.getPassengerType()== Passenger.PassengerType.PREMIUM){
            for(Destination destination:travelPackage.getDestinations()){
                TravelHistory travelHistory=new TravelHistory();
                travelHistory.setActivityName(destination.getActivities().getName());
                travelHistory.setActualPrice(destination.getActivities().getCost());
                travelHistory.setPricePaid(0); //for Premium paid 0
                travelHistory.setPackageName(travelPackage.getTravelPackageName());
                travelHistory.setDestinationName(destination.getName());
                travelHistory.setDestinationId(destination.getId());
                travelHistory.setPackageName(travelPackage.getTravelPackageName());
                travelHistories.add(travelHistory);
            }
            return passenger;

        }
       return passenger;

    }

    private Optional<TravelPackage> updateTravelPackage(Optional<TravelPackage> travelPackage) {
        //update package seat
        travelPackage.get().setSeatAvailable(travelPackage.get().getSeatAvailable()-1);
        travelPackage.get().setNumberOfPassengerEnrolled(travelPackage.get().getNumberOfPassengerEnrolled()+1);
        List<Destination> updatedDestination= updateDetination(travelPackage.get().getDestinations());
        travelPackage.get().setDestinations(updatedDestination);
        return travelPackage;
    }

    private List<Destination> updateDetination(List<Destination> destinations) {
        for (Destination destination : destinations) {
            destination.getActivities().setCapacity(destination.getActivities().getCapacity()-1);
        }
        return destinations;
    }

    private void checkDetinationCapicity(Activity activity) {
        if(activity.getCapacity()<=0){
            throw new NymbleTravelPlannerCommonException("this "+ activity.getName() +" has reached its maximum booking");
        }
    }

}
