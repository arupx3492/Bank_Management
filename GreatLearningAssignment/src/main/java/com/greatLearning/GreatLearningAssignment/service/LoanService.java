package com.greatLearning.GreatLearningAssignment.service;

import com.greatLearning.GreatLearningAssignment.dto.LoanApplicationRequestDTO;
import com.greatLearning.GreatLearningAssignment.entity.Loan;

import java.util.List;

public interface LoanService {

    public double calculateEMI(double loanAMount,double interestRate,int tenureInMonths);

    public Loan applyLoan(LoanApplicationRequestDTO requestDTO,String  email);

    public Loan approveOrDenyLoan(int loanId,double  amount);

    public List<Loan> getAllPendingLoan();
}
