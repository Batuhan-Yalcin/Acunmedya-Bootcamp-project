package com.batuhanyalcin.starter.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
}
