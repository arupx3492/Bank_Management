package com.greatLearning.GreatLearningAssignment.service;

import com.greatLearning.GreatLearningAssignment.entity.Chat;

import java.util.List;

public interface AdminService {

    public String deactivateAccount(int userId);
    public String activateAccount(int userId);
    public Chat sendMessageToCustomer(String message, int receiverId);

}
