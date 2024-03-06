package com.example.DeltaDrive_Delta.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequest {

    private String firstName;
    private String lastName;

    private String email;
    private String password;
    private Date birthday;
}
