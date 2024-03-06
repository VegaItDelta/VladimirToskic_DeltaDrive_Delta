package com.example.DeltaDrive_Delta.controller;

import com.example.DeltaDrive_Delta.entities.Ride;
import com.example.DeltaDrive_Delta.entities.User;
import com.example.DeltaDrive_Delta.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "/my-rides")
    public List<Ride> myRides(){
        return userService.getUserRides();
    }

    @GetMapping(value = "/all")
    public List<User> allUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

}
