package com.greatLearning.GreatLearningAssignment.serviceImpl;

import com.greatLearning.GreatLearningAssignment.entity.Chat;
import com.greatLearning.GreatLearningAssignment.entity.User;
import com.greatLearning.GreatLearningAssignment.exception.InvalidOperation;
import com.greatLearning.GreatLearningAssignment.repository.UserRepository;
import com.greatLearning.GreatLearningAssignment.service.AdminService;
import com.greatLearning.GreatLearningAssignment.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatService chatService;

    @Override
    public String deactivateAccount(int userId) {

        User user =userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found"));

        if(!user.isActive()){
            throw new InvalidOperation("User is already deactivated");
        }

        user.setActive(false);
        userRepository.save(user);

        return "User deactivated successfully";
    }

    @Override
    public String activateAccount(int userId) {
        User user =userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found"));

        if(user.isActive()){
            throw new InvalidOperation("User is already activated");
        }

        user.setActive(true);
        userRepository.save(user);

        return "User activated successfully";
    }

    @Override
    public Chat sendMessageToCustomer(String message, int receiverId) {
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));


        return chatService.sendChat(message,user.getId(),receiverId);
    }
}
