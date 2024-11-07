package com.greatLearning.GreatLearningAssignment.controller;

import com.greatLearning.GreatLearningAssignment.entity.Loan;
import com.greatLearning.GreatLearningAssignment.service.AccountService;
import com.greatLearning.GreatLearningAssignment.service.AdminService;
import com.greatLearning.GreatLearningAssignment.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AccountService accountService;

    @Autowired
    LoanService loanService;

    @PutMapping("/activate/{userId}")
    public ResponseEntity<String> activateAccount(@PathVariable int userId) {

        return ResponseEntity.ok(adminService.activateAccount(userId));
    }

    @PutMapping("/deactivate/{userId}")
    public ResponseEntity<String> deactivateAccount(@PathVariable int userId) {

        return ResponseEntity.ok(adminService.deactivateAccount(userId));
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestParam String accountNo,@RequestParam double amount){
        return ResponseEntity.ok(accountService.deposit(accountNo,amount));
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestParam String accountNo,@RequestParam double amount){
        return ResponseEntity.ok(accountService.withdraw(accountNo,amount));
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestParam String fromAccountNo,@RequestParam String toAccountNo,@RequestParam double amount){
        return ResponseEntity.ok(accountService.transfer(fromAccountNo,toAccountNo,amount));
    }

    @PutMapping("/loanApprove/{loanId}/{minAmount}")
    public ResponseEntity<Loan> ApproveOrDenyLoan(@PathVariable int loanId, @PathVariable double minAmount ){
        return ResponseEntity.ok(loanService.approveOrDenyLoan(loanId,minAmount));
    }

    @GetMapping("/loans")
    public ResponseEntity<List<Loan>> ApproveOrDenyLoan( ){
        return ResponseEntity.ok(loanService.getAllPendingLoan());
    }

    @PostMapping("/sendMessage/{userId}")
    public ResponseEntity<String> sendMessageToUser(@PathVariable int userId,@RequestParam String message){
        return ResponseEntity.ok(adminService.sendMessageToCustomer(message,userId).getMessage());
    }
}
