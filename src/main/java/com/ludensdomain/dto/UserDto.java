package com.ludensdomain.dto;

import lombok.Value;

@Value
public class UserDto {

    String id;
    String name;
    String password;
    String email;
    String dateOfBirth;
    String phoneNo;
}
