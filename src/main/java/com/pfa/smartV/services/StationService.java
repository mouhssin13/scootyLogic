package com.pfa.smartV.services;


import com.pfa.smartV.models.Station;
import com.pfa.smartV.models.User;
import com.pfa.smartV.repositories.StationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository){
        this.stationRepository = stationRepository;
    }

    public List<Station> getStations(){
        return stationRepository.findAll();
    }

    public Station getStationByID(Long id){
        Optional<Station> station = stationRepository.findById(id);
        if(station.isPresent()){
            return station.get();
        }
        else {
            throw new IllegalStateException("Station not found");
        }
    }

    public Station postStation(Station station){
        Optional<Station> stationByName = stationRepository.findStationByName(station.getName());
        if(stationByName.isPresent()){
            throw new IllegalStateException("Name of station already token");
        }
        return stationRepository.save(station);
    }

    @Transactional
    public Station updateStation(Long stationID, String name, double x, double y ){
        Station station = stationRepository.findById(stationID).orElseThrow(
                () -> new IllegalStateException("Station doesn't exist")
        );

        if(name != null && name.length() > 0 && !Objects.equals(station.getName(),name)){
            Optional<Station> stationByName = stationRepository.findStationByName(name);
            if(stationByName.isPresent()){
                throw new IllegalStateException("Station name already token");
            }
            station.setName(name);
        }
        if(x != 0 && x != station.getX() ) {
            station.setX(x);
        }

        if(y != 0 && y != station.getY() ) {
            station.setY(y);
        }

        return stationRepository.save(station);
    }

    @Transactional
    public Station desincrementNbrT(Long stationID ){
        Station station = stationRepository.findById(stationID).orElseThrow(
                () -> new IllegalStateException("Station doesn't exist")
        );
        if(station.getNbr_trottinette() > 0 ){
            station.setNbr_trottinette(station.getNbr_trottinette() - 1);
            return stationRepository.save(station);
        }else{
            throw new IllegalStateException("station empty");
        }
    }

    @Transactional
    public Station incrementNbrT(Long stationID ){
        Station station = stationRepository.findById(stationID).orElseThrow(
                () -> new IllegalStateException("Station doesn't exist")
        );

        if(station.getNbr_trottinette() < 7 ){
            station.setNbr_trottinette(station.getNbr_trottinette() + 1);
            return stationRepository.save(station);
        }else{
            throw new IllegalStateException("station full");
        }
    }

    public void deleteStation(Long stationID){
        boolean Exist = stationRepository.existsById(stationID);
        if(Exist){
            stationRepository.deleteById(stationID);
        }
        else{
            throw new IllegalStateException("this Station already not exist");
        }
    }


}
