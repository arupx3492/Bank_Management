package com.greatLearning.GreatLearningAssignment.service;

import com.greatLearning.GreatLearningAssignment.dto.LoginRequest;
import com.greatLearning.GreatLearningAssignment.dto.UserDTO;
import com.greatLearning.GreatLearningAssignment.entity.User;

public interface AuthService {

    public String registerUser(UserDTO userDTO);
    public String loginUser(LoginRequest loginRequest);

    public String logoutUser(String authHeader);
}
