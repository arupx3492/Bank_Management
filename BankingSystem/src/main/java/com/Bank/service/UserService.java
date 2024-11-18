package com.Bank.service;

import com.Bank.entity.Chat;

import java.util.List;

public interface UserService {


    public String depositMoney(double amount);
    public String withdrawMoney(double amount);
    public String transferMoney(String accountNo,double amount);
    public String sendMessage(String message);
    public List<Chat> getMessages();


}
