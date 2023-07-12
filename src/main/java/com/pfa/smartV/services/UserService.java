package com.pfa.smartV.services;


import com.pfa.smartV.models.User;
import com.pfa.smartV.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(long Id){
        Optional<User> user = userRepository.findById(Id);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new IllegalStateException("User not found");
        }
    }




}
