package com.greatLearning.GreatLearningAssignment.service;

public interface AccountService {

    public String deposit(String accountNo,double amount);
    public String withdraw(String accountNo,double amount);

    public String transfer(String fromAccountNo,String toAccountNo,double amount);
}
