package com.Bank.dto;

import lombok.Data;

@Data
public class LoanApplicationRequestDTO {

    private double amount;
    private double interestRate;
    private int durationMonths;
}
