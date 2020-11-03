package com.ludensdomain.dto;

import lombok.*;

import java.util.Date;

@Value
public class UserDto {

    Long id;
    String name;
    String password;
    String email;
    Date dateOfBirth;
    String phoneNo;
    String role;
}
