package com.pfa.smartV.repositories;


import com.pfa.smartV.models.Trottinette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrottinetteRepository
        extends JpaRepository<Trottinette, Long> {

}
