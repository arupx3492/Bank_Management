package com.Bank.service;

import com.Bank.dto.LoginRequest;
import com.Bank.dto.UserDTO;

public interface AuthService {

    public String registerUser(UserDTO userDTO);
    public String loginUser(LoginRequest loginRequest);

    public String logoutUser(String authHeader);
}
