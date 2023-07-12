package com.pfa.smartV.controllers;

import java.lang.Math;
import com.pfa.smartV.models.Station;
import com.pfa.smartV.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/smartV/stations")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService){
        this.stationService = stationService;
    }

    @GetMapping("get")
    public List<Station> getAllStations(){
        List<Station> Stations =  stationService.getStations();
       for(int i=0; i<Stations.size(); i++ ){
           if(Stations.get(i).getNbr_trottinette() ==0){
               Stations.remove(i);
               i=i-1;
           }
       }
       return Stations;
    }
    @GetMapping("return")
    public List<Station> getAllStation(){
        List<Station> Stations =  stationService.getStations();
        for(int i=0; i<Stations.size(); i++ ){
            if(Stations.get(i).getNbr_trottinette() ==7){
                Stations.remove(i);
                i=i-1;
            }
        }
        return Stations;
    }
    @GetMapping("closest")
    public Station getClosest(@RequestParam(required = true) double x, @RequestParam(required = true) double y){
        List<Station> Stations = stationService.getStations();
        double distance = calculateDistance(x,y,Stations.get(0).getX(),Stations.get(0).getY());
        Station st = new Station();
        for(int i=1; i<Stations.size(); i++){
            double dis = calculateDistance(x,y,Stations.get(i).getX(),Stations.get(i).getY());
            if(distance >= dis){
                distance = dis;
                st = Stations.get(i);
            }
        }
        return st;
    }

    @GetMapping
    public List<Station> getAllStationsAdmin(){
        return   stationService.getStations();
    }

    @GetMapping("{id}")
    public ResponseEntity<Station> getStationById(@PathVariable("id") Long id){
        return new ResponseEntity<Station>(stationService.getStationByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Station> postStation(@RequestBody Station station){
        ResponseEntity<Station> stationResponseEntity = new ResponseEntity<Station>(stationService.postStation(station),HttpStatus.CREATED);
        return stationResponseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<Station> updateStation(@PathVariable("id") Long stationID, @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) double x, @RequestParam(required = false) double y){
        return new ResponseEntity<Station>(stationService.updateStation(stationID,name,x,y),HttpStatus.OK);
    }

    @PutMapping("inc/{id}")
    public ResponseEntity<Station> incNbrT(@PathVariable("id") Long stationID){
        return new ResponseEntity<Station>(stationService.incrementNbrT(stationID), HttpStatus.OK);
    }
    @PutMapping("desinc/{id}")
    public ResponseEntity<Station> desincNbrT(@PathVariable("id") Long stationID){
        return new ResponseEntity<Station>(stationService.desincrementNbrT(stationID), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStation(@PathVariable("id") Long stationID){
        stationService.deleteStation(stationID);
        return new ResponseEntity<String>("Station deleted successfully", HttpStatus.OK);
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Radius of the Earth in kilometers
        double earthRadius = 6371;

        // Convert latitude and longitude values from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate the differences between coordinates
        double latDiff = lat2Rad - lat1Rad;
        double lonDiff = lon2Rad - lon1Rad;

        // Calculate the Haversine formula
        double a = Math.pow(Math.sin(latDiff / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(lonDiff / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }

}
