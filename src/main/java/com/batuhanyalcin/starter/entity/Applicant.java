package com.batuhanyalcin.starter.entity;

import com.batuhanyalcin.starter.core.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applicants")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Applicant extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "about")
    private String about;
}
