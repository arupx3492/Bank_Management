package com.Bank.repository;

import com.Bank.entity.Loan;
import com.Bank.entity.User;
import com.Bank.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {

    List<Loan> findByUser(User user);
    List<Loan> findByStatus(LoanStatus loanStatus);
    List<Loan> findAllByStatusOrderByUser(LoanStatus loanStatus);
}
