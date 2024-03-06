package com.example.DeltaDrive_Delta.controller;

import com.example.DeltaDrive_Delta.dto.JwtAuthenticationResponse;
import com.example.DeltaDrive_Delta.dto.RefreshTokenRequest;
import com.example.DeltaDrive_Delta.dto.SignInRequest;
import com.example.DeltaDrive_Delta.dto.SignUpRequest;
import com.example.DeltaDrive_Delta.entities.User;
import com.example.DeltaDrive_Delta.services.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signup(
            @RequestBody SignUpRequest signUpRequest
    ){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return  ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest request){
        return  ResponseEntity.ok(authenticationService.refreshToken(request));
    }
}
