package com.pfa.smartV.services;


import com.pfa.smartV.models.RideResponse;
import com.pfa.smartV.models.Trottinette;
import com.pfa.smartV.models.User;
import com.pfa.smartV.repositories.RideRepository;
import com.pfa.smartV.repositories.TrottinetteRepository;
import com.pfa.smartV.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pfa.smartV.models.Ride;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {
    private final RideRepository rideRepository;
    private final TrottinetteRepository trottinetteRepository;
    private final UserRepository userRepository;
    @Autowired
    public RideService(RideRepository rideRepository,TrottinetteRepository trottinetteRepository , UserRepository userRepository){
        this.rideRepository = rideRepository;
        this.trottinetteRepository = trottinetteRepository;
        this.userRepository = userRepository;
    }

    public List<Ride> getAllRides(){
        return rideRepository.findAll();
    }
    public RideResponse getRideByID(Long id){
        Optional<Ride> rideByID = rideRepository.findById(id);
        if(rideByID.isPresent()){
            RideResponse rideResponse = new RideResponse();
            rideResponse.setId(rideByID.get().getId());
            rideResponse.setDateDep(rideByID.get().getDateDep());
            rideResponse.setDateArr(rideByID.get().getDateArr());
            rideResponse.setPrice(rideByID.get().getPrice());
            rideResponse.setDuree(rideByID.get().getDuree());
            rideResponse.setTrottinetteID(rideByID.get().getTrottinette().getId());
            rideResponse.setUserID(rideByID.get().getUser().getId());
            return rideResponse;
        }else{
            throw new IllegalStateException("This Ride doesn't Exist");
        }
    }



    public Ride addRide(Ride ride){
        return rideRepository.save(ride);
    }

    public RideResponse getRideByUser(long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("User doesn't exist")
        );
        List<Ride> ride = rideRepository.findAllRidesByUser(user);

        int x =0;
        for(int i=1; i<ride.size(); i++){
            x+= 1;
        }

        Ride rideByU = ride.get(x);
        RideResponse rideres= new RideResponse();
        rideres.setUserID(rideByU.getUser().getId());
        rideres.setId(rideByU.getId());
        rideres.setPrice(rideByU.getPrice());
        rideres.setDuree(rideByU.getDuree());
        rideres.setDateDep(rideByU.getDateDep());
        rideres.setDateArr(rideByU.getDateArr());
        rideres.setTrottinetteID(rideByU.getTrottinette().getId());
        if(rideres.getDateArr() == null) {
            return rideres;
        }else{
            RideResponse rideEmpty = new RideResponse(rideres.getId(),rideres.getDateDep(), LocalTime.now(), 10, rideres.getTrottinetteID(), rideres.getUserID(), rideres.getDuree() );
            return rideEmpty;
        }
    }

    @Transactional
    public RideResponse updateRidePrice(Long id){
        Ride ride = rideRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Ride doesn't exist")
        );

        float price = (float) (ride.getDuree()*0.5);
        ride.setPrice(price);
        rideRepository.save(ride);
        return getRideByID(ride.getId());
    }

    @Transactional
    public RideResponse updateRideTro(Long id, Long troID){
        Ride ride = rideRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Ride doesn't exist")
        );
        Trottinette trottinette = trottinetteRepository.findById(troID).orElseThrow(
                () -> new IllegalStateException("troti doesn't exist")
        );

        ride.setTrottinette(trottinette);
        rideRepository.save(ride);
        return getRideByID(ride.getId());
    }
    @Transactional
    public RideResponse updateRideDateArr(Long id ){
        Ride ride = rideRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Ride doesn't exist")
        );

        LocalTime arr = LocalTime.now();
        ride.setDateArr(arr);
        rideRepository.save(ride);
        return getRideByID(ride.getId());
    }

    public void deleteRide(Long id){
        boolean exist = rideRepository.existsById(id);
        if(exist){
            rideRepository.deleteById(id);
        }else{
            throw new IllegalStateException("This ride already doesn't exist");
        }
    }
}
