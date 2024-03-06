package com.example.DeltaDrive_Delta.services.impl;

import com.example.DeltaDrive_Delta.Exceptions.UserNotFoundException;
import com.example.DeltaDrive_Delta.entities.Ride;
import com.example.DeltaDrive_Delta.entities.User;
import com.example.DeltaDrive_Delta.repository.UserRepository;
import com.example.DeltaDrive_Delta.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email)  {
                return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public List<Ride> getUserRides() {
        User user = getLoggedUser();
        return user.getRides();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getLoggedUser(){
        try {
            return getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (Exception e) {
            if (e instanceof NullPointerException ) {
                return null;
            }
            e.printStackTrace();
            throw new UserNotFoundException("User not found");
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
    }
}
