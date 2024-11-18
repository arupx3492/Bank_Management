package com.Bank.serviceImpl;

import com.Bank.dto.LoanApplicationRequestDTO;
import com.Bank.entity.Account;
import com.Bank.entity.Loan;
import com.Bank.entity.User;
import com.Bank.enums.AccountType;
import com.Bank.enums.LoanStatus;
import com.Bank.exception.AccountNotFoundException;
import com.Bank.repository.AccountRepository;
import com.Bank.repository.LoanRepository;
import com.Bank.repository.UserRepository;
import com.Bank.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public double calculateEMI(double loanAMount, double interestRate, int tenureInMonths) {
        double monthlyInterestRate = interestRate / (12 * 100);
      double emi=(loanAMount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureInMonths)) /
                (Math.pow(1 + monthlyInterestRate, tenureInMonths) - 1);

      return BigDecimal.valueOf(emi).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public Loan applyLoan(LoanApplicationRequestDTO requestDTO, String email) {
        User user=userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not Found Exception"));
        double emiAmount = calculateEMI(requestDTO.getAmount(), requestDTO.getInterestRate(), requestDTO.getDurationMonths());

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setAmount(requestDTO.getAmount());
        loan.setDurationMonths(requestDTO.getDurationMonths());
        loan.setAppliedDate(new Date());
        loan.setStatus(LoanStatus.PENDING);
        loan.setInterestRate(requestDTO.getInterestRate());
        loan.setEmiAmount(emiAmount);
        return loanRepository.save(loan);
    }

    @Override
    public Loan approveOrDenyLoan(int loanId,double amount) {

        Loan loan=loanRepository.findById(loanId).orElseThrow(()-> new AccountNotFoundException("Invalid Loan no"));

        Account account=accountRepository.findByUserAndType(loan.getUser(), AccountType.SAVING).orElseThrow(()-> new AccountNotFoundException("Account Not Found for this loan"));

        if(account.getBalance()>=amount){
            loan.setStatus(LoanStatus.APPROVED);
        }else{
            loan.setStatus(LoanStatus.DENIED);
        }


        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getAllPendingLoan() {
        return loanRepository.findAllByStatusOrderByUser(LoanStatus.PENDING);
    }
}
