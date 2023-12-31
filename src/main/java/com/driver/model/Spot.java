package com.driver.model;


import com.driver.repository.SpotRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="spot")
public class Spot {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Enumerated(value=EnumType.STRING)
     private SpotType spotType;

    private  int pricePerHour;

     private Boolean  occupied;


     @ManyToOne
    @JoinColumn
    private ParkingLot  parkingLot;

   @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL)
    private  List<Reservation> reservationList = new ArrayList<>();


    public Spot() {
    }


    public Spot(int id, SpotType spotType, int pricePerHour, Boolean occupied, ParkingLot parkingLot, List<Reservation> reservationList) {
        this.id = id;
        this.spotType = spotType;
        this.pricePerHour = pricePerHour;
       this.occupied = occupied;
        this.parkingLot = parkingLot;
        this.reservationList = reservationList;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
