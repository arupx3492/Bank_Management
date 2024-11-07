package com.greatLearning.GreatLearningAssignment.serviceImpl;

import com.greatLearning.GreatLearningAssignment.entity.Account;
import com.greatLearning.GreatLearningAssignment.entity.Transaction;
import com.greatLearning.GreatLearningAssignment.entity.User;
import com.greatLearning.GreatLearningAssignment.enums.TransactionType;
import com.greatLearning.GreatLearningAssignment.exception.AccountNotFoundException;
import com.greatLearning.GreatLearningAssignment.exception.InsufficientBalanceException;
import com.greatLearning.GreatLearningAssignment.repository.AccountRepository;
import com.greatLearning.GreatLearningAssignment.repository.TransactionRepository;
import com.greatLearning.GreatLearningAssignment.service.AccountService;
import com.greatLearning.GreatLearningAssignment.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

private static final Logger logger= LoggerFactory.getLogger(Transaction.class);
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    EmailService emailService;


    @Override
    public String deposit(String accountNo, double amount) {

        Account account=accountRepository.findByAccountNumber(accountNo).orElseThrow(()-> new AccountNotFoundException("Account Not Found"));
        account.setBalance(account.getBalance()+amount);

        accountRepository.save(account);

        Transaction transaction=new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setSender(account.getUser());
        transaction.setReceiver(account.getUser());

        transactionRepository.save(transaction);
        return "Deposit Successful . Current Balance is "+account.getBalance();
    }

    @Override
    public String withdraw(String accountNo, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNo)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal.");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setSender(account.getUser());
        transaction.setReceiver(account.getUser()); // Receiver is the same account for withdrawal

        transactionRepository.save(transaction);

        if(account.getBalance()==0){

            User user=account.getUser();
            String message="Dear [Customer's Name],\n" +
                    "\n" +
                    "We hope this message finds you well.\n" +
                    "\n" +
                    "We wanted to inform you that your current account balance is zero. To continue enjoying our services without interruption, we kindly ask you to add funds to your account at your earliest convenience.";
            emailService.sendEmail(user.getEmail(),"Important Update: Your Account Balance is Zero",message);
        }

        return "Withdrawal Successful. Current Balance is " + account.getBalance();
    }

    @Override
    public String transfer(String senderAccountNo, String receiverAccountNo, double amount) {

//        logger.info("this is transfer method");

        Account senderAccount = accountRepository.findByAccountNumber(senderAccountNo)
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found"));

        Account receiverAccount = accountRepository.findByAccountNumber(receiverAccountNo)
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found"));

        if (senderAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for transfer.");
        }
//        logger.info("this is after gettting account");
        // Deduct from sender's account
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
        accountRepository.saveAll(List.of(senderAccount,receiverAccount));

//logger.info("after saving account");
        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setSender(senderAccount.getUser());
        transaction.setReceiver(receiverAccount.getUser());

//        logger.info("before saving transaction");
        transactionRepository.save(transaction);
//        logger.info("after saving transaction");

        if(senderAccount.getBalance()==0){

            User user=senderAccount.getUser();
            String message="Dear [Customer's Name],\n" +
                    "\n" +
                    "We hope this message finds you well.\n" +
                    "\n" +
                    "We wanted to inform you that your current account balance is zero. To continue enjoying our services without interruption, we kindly ask you to add funds to your account at your earliest convenience.";
            emailService.sendEmail(senderAccount.getUser().getEmail(),"Important Update: Your Account Balance is Zero",message);
        }

        return "Transfer Successful. Your new balance is " + senderAccount.getBalance();
    }
}
