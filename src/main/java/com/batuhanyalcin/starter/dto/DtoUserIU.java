package com.batuhanyalcin.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserIU {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int nationalIdentity;
    private String dateOfBirth;
}
