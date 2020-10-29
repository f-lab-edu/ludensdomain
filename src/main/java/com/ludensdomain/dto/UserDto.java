package com.ludensdomain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    String id;
    String name;
    String password;
    String email;
    String dateOfBirth;
    String phoneNo;
    String role;
}
