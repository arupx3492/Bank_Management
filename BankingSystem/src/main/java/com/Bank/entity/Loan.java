package com.Bank.entity;

import com.Bank.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(nullable = false)
    private double amount;

    private double interestRate;
    private int durationMonths;

    private double emiAmount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private Date appliedDate;


}
