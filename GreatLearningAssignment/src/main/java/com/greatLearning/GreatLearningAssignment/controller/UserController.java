package com.greatLearning.GreatLearningAssignment.controller;

import com.greatLearning.GreatLearningAssignment.dto.LoanApplicationRequestDTO;
import com.greatLearning.GreatLearningAssignment.entity.Chat;
import com.greatLearning.GreatLearningAssignment.entity.Loan;
import com.greatLearning.GreatLearningAssignment.exception.InsufficientBalanceException;
import com.greatLearning.GreatLearningAssignment.service.EmailService;
import com.greatLearning.GreatLearningAssignment.service.LoanService;
import com.greatLearning.GreatLearningAssignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoanService loanService;

    @Autowired
    EmailService emailService;


    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        System.out.println("This is demo method");

        try {
            emailService.sendEmail("arupx3492@gmail.com", "Testing", "This is body");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        throw new InsufficientBalanceException("Insufficient Balance");
//        return ResponseEntity.ok("working");
    }

    @PutMapping("/deposit/{amount}")
    public ResponseEntity<String> deposit(@PathVariable double amount) {


        return ResponseEntity.ok(userService.depositMoney(amount));


    }

    @PutMapping("/withdraw/{amount}")
    public ResponseEntity<String> withdraw(@PathVariable double amount) {


        return ResponseEntity.ok(userService.withdrawMoney(amount));


    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String accountNo, @RequestParam double amount) {


        return ResponseEntity.ok(userService.transferMoney(accountNo, amount));


    }

    @PostMapping("/applyLoan")
    public ResponseEntity<String> applyForLoan(@RequestBody LoanApplicationRequestDTO applicationRequestDTO){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        Loan loan=loanService.applyLoan(applicationRequestDTO,email);
        return ResponseEntity.ok("Loan application submitted successfully with ID: "+loan.getId());
    }

    @GetMapping("/calculateEMI")
    public ResponseEntity<String> calculateEMI(@RequestParam double amount,@RequestParam double interestRate,@RequestParam int months){
        double emi= loanService.calculateEMI(amount,interestRate,months);
        return ResponseEntity.ok("Your Monthly EMI is: "+emi);
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody String message){
        return ResponseEntity.ok(userService.sendMessage(message));
    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<Chat>> sendMessage( ){
        return ResponseEntity.ok(userService.getMessages());
    }
}
