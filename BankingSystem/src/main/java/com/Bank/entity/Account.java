package com.Bank.entity;

import com.Bank.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name="user_Id",nullable=false)
    private User user;

    @Enumerated(EnumType.STRING)
    private AccountType type;
    private double balance;
}
