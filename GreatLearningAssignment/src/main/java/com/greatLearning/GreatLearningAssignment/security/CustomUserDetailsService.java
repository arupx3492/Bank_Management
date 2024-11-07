package com.greatLearning.GreatLearningAssignment.security;

import com.greatLearning.GreatLearningAssignment.entity.User;
import com.greatLearning.GreatLearningAssignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("load user by username mehtod");
        User user=userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User Not found"));

        System.out.println("This is user:- "+user);

        return org.springframework.security.core.userdetails.User.builder()
                .roles(String.valueOf(user.getRole()))
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
