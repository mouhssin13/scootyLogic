package com.pfa.smartV.repositories;


import com.pfa.smartV.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository
        extends JpaRepository <Station, Long> {

    Optional<Station> findStationByName(String name);
}
