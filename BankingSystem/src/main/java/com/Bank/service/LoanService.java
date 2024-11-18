package com.Bank.service;

import com.Bank.dto.LoanApplicationRequestDTO;
import com.Bank.entity.Loan;

import java.util.List;

public interface LoanService {

    public double calculateEMI(double loanAMount,double interestRate,int tenureInMonths);

    public Loan applyLoan(LoanApplicationRequestDTO requestDTO,String  email);

    public Loan approveOrDenyLoan(int loanId,double  amount);

    public List<Loan> getAllPendingLoan();
}
