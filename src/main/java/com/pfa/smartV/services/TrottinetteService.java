package com.pfa.smartV.services;


import com.pfa.smartV.models.Station;
import com.pfa.smartV.models.Trottinette;
import com.pfa.smartV.repositories.StationRepository;
import com.pfa.smartV.repositories.TrottinetteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrottinetteService {

    private final TrottinetteRepository trottinetteRepository;
    private final StationRepository stationRepository;

    @Autowired
    public TrottinetteService(TrottinetteRepository trottinetteRepository, StationRepository stationRepository){
        this.trottinetteRepository = trottinetteRepository;
        this.stationRepository = stationRepository;
    }


    public List<Trottinette> getAllTrottinettes(){
        List<Trottinette>  trottinettes = trottinetteRepository.findAll();
        trottinettes.forEach(trottinette -> trottinette.getStation().getName());
        return trottinettes;
    }

    public Trottinette getTrottinetteById(Long id){
        Optional<Trottinette> trottinette = trottinetteRepository.findById(id);
        if(trottinette.isPresent()){
            return trottinette.get();
        }else{
            throw new IllegalStateException("Trottinette doesn't exist");
        }
    }

    public Trottinette postTorttinette(Trottinette trottinette){
        return trottinetteRepository.save(trottinette);
    }

    @Transactional
    public Trottinette updateTrottinette(Long id, double x, double y){
        Trottinette trottinette = trottinetteRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Trottinette doesn't exist")
        );
        if(x != 0 && x != trottinette.getX() ) {
            trottinette.setX(x);
        }
        if(y != 0 && y != trottinette.getY()){
            trottinette.setY(y);
        }

        return trottinetteRepository.save(trottinette);
    }
    @Transactional
    public Trottinette updateTrottinetteStation(Long troID, Long staID){
        Trottinette trottinette = trottinetteRepository.findById(troID).orElseThrow(
                () -> new IllegalStateException("Trottinette doesn't exist")
        );
        Station station = stationRepository.findById(staID).orElseThrow(
                () -> new IllegalStateException("Station doesn't exist")
        );

        trottinette.setStation(station);
        return trottinetteRepository.save(trottinette);
    }

    public void deleteTrottinette(Long id){
        boolean Exist = trottinetteRepository.existsById(id);
        if(Exist){
            trottinetteRepository.deleteById(id);
        }
        else{
            throw new IllegalStateException("this trottinette already not exist");
        }
    }

}
