package com.greatLearning.GreatLearningAssignment.service;

import com.greatLearning.GreatLearningAssignment.entity.Chat;

import java.util.List;

public interface ChatService {

    List<Chat> getChatMessage(int userId);
    Chat sendChat(String message,int senderId,int receiverId);
}
