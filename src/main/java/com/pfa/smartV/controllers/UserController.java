package com.pfa.smartV.controllers;


import com.pfa.smartV.models.User;
import com.pfa.smartV.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/smartV/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getStudentById(@PathVariable("id") Long userId){
        return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
    }


}
