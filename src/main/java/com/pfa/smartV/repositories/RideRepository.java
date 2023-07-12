package com.pfa.smartV.repositories;


import com.pfa.smartV.models.Ride;
import com.pfa.smartV.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RideRepository
                extends JpaRepository<Ride, Long> {
    List<Ride> findAllRidesByUser(User user);
}
