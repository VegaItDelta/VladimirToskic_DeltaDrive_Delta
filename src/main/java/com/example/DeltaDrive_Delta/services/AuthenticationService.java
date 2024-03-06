package com.example.DeltaDrive_Delta.services;

import com.example.DeltaDrive_Delta.dto.SignUpRequest;
import com.example.DeltaDrive_Delta.entities.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
}
