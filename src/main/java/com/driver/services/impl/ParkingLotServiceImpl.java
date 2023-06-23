package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot= new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
      parkingLotRepository1.save(parkingLot);
      return  parkingLot;


    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        Spot spot = new Spot();
        spot.setPricePerHour(pricePerHour);
        spot.setOccupied(false);
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();


        spot.setParkingLot(parkingLot);

        if(numberOfWheels<=2){
                                spot.setSpotType(SpotType.TWO_WHEELER);}
        else if(numberOfWheels<=4){
                                    spot.setSpotType(SpotType.FOUR_WHEELER);}
        else {spot.setSpotType(SpotType.OTHERS);}

         List<Spot> spotList = parkingLot.getSpotList();
         spotList.add(spot);

        parkingLot.setSpotList(spotList);
        parkingLotRepository1.save(parkingLot);

           return  spot; }

    @Override
    public ParkingLot getParkingLot(int parkingLotId) {
        return parkingLotRepository1.findById(parkingLotId).get();
    }


    @Override
        public void deleteSpot(int spotId) {


        spotRepository1.deleteById(spotId);


    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
     ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();

        Spot spot = spotRepository1.findById(spotId).get();

     Spot updatedSpot  =  null;

    List<Spot> spotList= parkingLot.getSpotList();
     if(spotList.contains(spot)){
    spot.setPricePerHour(pricePerHour);
    updatedSpot=spotRepository1.save(spot);
        }

     parkingLot.setSpotList(spotList);

     parkingLotRepository1.save(parkingLot);
     return updatedSpot;
         }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);

    }
}
