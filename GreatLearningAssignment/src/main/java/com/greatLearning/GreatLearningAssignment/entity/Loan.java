package com.greatLearning.GreatLearningAssignment.entity;

import com.greatLearning.GreatLearningAssignment.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;
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
