package com.greatLearning.GreatLearningAssignment.dto;

import com.greatLearning.GreatLearningAssignment.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class UserDTO {


    private String password;


    private String email;

    private String role;
}
