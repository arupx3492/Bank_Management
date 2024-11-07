package com.greatLearning.GreatLearningAssignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greatLearning.GreatLearningAssignment.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private String  userName;
    @JsonIgnore
    private String password;
    private String email;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private Role role;



}
