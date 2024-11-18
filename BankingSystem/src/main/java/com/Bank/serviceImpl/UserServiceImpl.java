package com.Bank.serviceImpl;

import com.Bank.entity.Account;
import com.Bank.entity.Chat;
import com.Bank.entity.User;
import com.Bank.enums.AccountType;
import com.Bank.exception.AccountNotFoundException;
import com.Bank.repository.AccountRepository;
import com.Bank.repository.UserRepository;
import com.Bank.service.AccountService;
import com.Bank.service.ChatService;
import com.Bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatService chatService;

    @Override
    public String depositMoney(double amount) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        Account account = accountRepository.findByUserAndType(user, AccountType.SAVING).orElseThrow(() -> new AccountNotFoundException("Account Not found"));

        return accountService.deposit(account.getAccountNumber(), amount);


    }

    @Override
    public String withdrawMoney(double amount) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        Account account = accountRepository.findByUserAndType(user, AccountType.SAVING).orElseThrow(() -> new AccountNotFoundException("Account Not found"));

        return accountService.withdraw(account.getAccountNumber(), amount);
    }

    @Override
    public String transferMoney(String accountNo, double amount) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        Account account = accountRepository.findByUserAndType(user, AccountType.SAVING).orElseThrow(() -> new AccountNotFoundException("Account Not found"));

        return accountService.transfer(account.getAccountNumber(), accountNo, amount);
    }

    @Override
    public String sendMessage(String message) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

         Chat chat=chatService.sendChat(message,user.getId(),0);
        return chat.getMessage();
    }

    @Override
    public List<Chat> getMessages() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return chatService.getChatMessage(user.getId());
    }
}
