package com.greatLearning.GreatLearningAssignment.serviceImpl;

import com.greatLearning.GreatLearningAssignment.entity.Chat;
import com.greatLearning.GreatLearningAssignment.entity.User;
import com.greatLearning.GreatLearningAssignment.enums.Role;
import com.greatLearning.GreatLearningAssignment.repository.ChatRepository;
import com.greatLearning.GreatLearningAssignment.repository.UserRepository;
import com.greatLearning.GreatLearningAssignment.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Chat> getChatMessage(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not Found"));


        return chatRepository.findBySenderOrderByTimestamp(user);
    }

    @Override
    public Chat sendChat(String message,int senderId,int receiverId) {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new UsernameNotFoundException("User not Found"));

        Chat chat=new Chat();
        chat.setSender(sender);
        if(sender.getRole()== Role.ADMIN){
            User receiver = userRepository.findById(receiverId).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
            chat.setReceiver(receiver);
        }
        chat.setMessage(message);
        chat.setSenderRole(String.valueOf(sender.getRole()));
        chat.setTimestamp(LocalDateTime.now());

        return chatRepository.save(chat);
    }
}
