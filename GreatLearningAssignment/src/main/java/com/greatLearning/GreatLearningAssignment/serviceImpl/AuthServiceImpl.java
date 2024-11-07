package com.greatLearning.GreatLearningAssignment.serviceImpl;

import com.greatLearning.GreatLearningAssignment.dto.LoginRequest;
import com.greatLearning.GreatLearningAssignment.dto.UserDTO;
import com.greatLearning.GreatLearningAssignment.entity.Account;
import com.greatLearning.GreatLearningAssignment.entity.Token;
import com.greatLearning.GreatLearningAssignment.entity.User;
import com.greatLearning.GreatLearningAssignment.enums.AccountType;
import com.greatLearning.GreatLearningAssignment.enums.Role;
import com.greatLearning.GreatLearningAssignment.repository.AccountRepository;
import com.greatLearning.GreatLearningAssignment.repository.TokenRepository;
import com.greatLearning.GreatLearningAssignment.repository.UserRepository;
import com.greatLearning.GreatLearningAssignment.security.JwtUtil;
import com.greatLearning.GreatLearningAssignment.service.AuthService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SecureRandom;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AccountRepository accountRepository;

    private static final SecureRandom random = new SecureRandom();




    @Override
    public String registerUser(UserDTO userDTO) {

        if(userRepository.existsByEmail(userDTO.getEmail())){
            return "Email is already in use";
        }
        User user=new User();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setEmail(userDTO.getEmail());
        user.setActive(true);
        userRepository.save(user);
        if(user.getRole()==Role.CUSTOMER){
            Account account=new Account();
            account.setUser(user);

            account.setAccountNumber(generateAccountNumber());
            account.setBalance(0);
            account.setType(AccountType.SAVING);

            accountRepository.save(account);
        }






        return "user registered successfully";
    }

    String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }

    @Override
    public String loginUser(LoginRequest loginRequest) {

        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );

        System.out.println("This is after authentication");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtUtil.generateToken(authentication.getName());

        Token tokenEntity=new Token();
        tokenEntity.setToken(token);
        tokenEntity.setExpiryDate(jwtUtil.extractExpiration(token));
        tokenEntity.setBlacklisted(false);

        tokenRepository.save(tokenEntity);

        return token;

//        try {
//
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//            );
//
//            System.out.println("This is after authentication");
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String token = jwtUtil.generateToken(authentication.getName());
//
//            Token tokenEntity = new Token();
//            tokenEntity.setToken(token);
//            tokenEntity.setExpiryDate(jwtUtil.extractExpiration(token));
//            tokenEntity.setBlacklisted(false);
//
//            tokenRepository.save(tokenEntity);
//
//            return token;
//
//        } catch (Exception e) {
//            System.out.println("Authentication failed: " + e.getMessage());
//            e.printStackTrace(); // Print full stack trace for debugging
//            return null; // Or throw a custom exception or response
//        }
    }

    @Override
    public String logoutUser(String authHeader) {

        if(authHeader!= null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            Token storedToken=tokenRepository.findByToken(token);
            if(storedToken!=null){
                storedToken.setBlacklisted(true);
                tokenRepository.save(storedToken);

                return "Logout Successful";
            }
        }



        return "Invalid Token";
    }
}
