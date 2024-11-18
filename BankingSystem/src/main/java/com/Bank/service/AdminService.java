package com.Bank.service;

import com.Bank.entity.Chat;

public interface AdminService {

    public String deactivateAccount(int userId);
    public String activateAccount(int userId);
    public Chat sendMessageToCustomer(String message, int receiverId);

}
