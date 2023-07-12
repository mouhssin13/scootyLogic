package com.pfa.smartV.controllers;


import com.pfa.smartV.models.Ride;
import com.pfa.smartV.models.RideResponse;
import com.pfa.smartV.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/smartV/ride")
public class RideController {

    private final RideService rideService;

    @Autowired
    public RideController(RideService rideService){
        this.rideService = rideService;
    }

    @GetMapping
    public List<RideResponse> getAllRides(){
        List<Ride> rides = rideService.getAllRides();
        List<RideResponse> ridesResponse = new ArrayList<RideResponse>();
        for(int i=0; i< rides.size(); i++){
            RideResponse ride = new RideResponse();
            ride.setId(rides.get(i).getId());
            ride.setDateDep(rides.get(i).getDateDep());
            ride.setDateArr(rides.get(i).getDateArr());
            ride.setPrice(rides.get(i).getPrice());
            ride.setDuree(rides.get(i).getDuree());
            ride.setTrottinetteID(rides.get(i).getTrottinette().getId());
            ride.setUserID(rides.get(i).getUser().getId());
            ridesResponse.add(ride);
        }
        return ridesResponse;
    }

    @GetMapping("{id}")
    public ResponseEntity<RideResponse> getRideByID(@PathVariable("id") Long id){
        return new ResponseEntity<RideResponse>(rideService.getRideByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ride> addNewRide(@RequestBody Ride ride){
        ride.setDateDep(LocalTime.now());
        return new ResponseEntity<Ride>(rideService.addRide(ride), HttpStatus.CREATED);
    }

    @PutMapping("/price/{id}")
    public ResponseEntity<RideResponse> updatePrice(@PathVariable("id") Long id){
        return new ResponseEntity<RideResponse>(rideService.updateRidePrice(id), HttpStatus.OK);
    }
    @PutMapping("/arr/{id}")
    public ResponseEntity<RideResponse> updateDateArr(@PathVariable("id") Long id){
        return  new ResponseEntity<RideResponse>(rideService.updateRideDateArr(id), HttpStatus.OK);
    }

    @PutMapping("/tro/{id}/{troID}")
    public ResponseEntity<RideResponse> updateDateTro(@PathVariable("id") Long id, @PathVariable("troID") Long troID){
        return new ResponseEntity<RideResponse>(rideService.updateRideTro(id,troID), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<RideResponse> findRideUser(@PathVariable("id") Long id){
        return  new ResponseEntity<RideResponse>(rideService.getRideByUser(id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRide(@PathVariable("id") Long id){
        rideService.deleteRide(id);
        return new ResponseEntity<String>("Ride deleted successfully", HttpStatus.OK);
    }
}
