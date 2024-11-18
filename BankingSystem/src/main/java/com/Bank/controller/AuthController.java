package com.Bank.controller;

import com.Bank.dto.LoginRequest;
import com.Bank.dto.UserDTO;
import com.Bank.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){

        return ResponseEntity.ok(authService.registerUser(userDTO));
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        System.out.println("This is login method");
        System.out.println(loginRequest);
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader){
        return ResponseEntity.ok(authService.logoutUser(authHeader));
    }

}
