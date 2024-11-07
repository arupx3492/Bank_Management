package com.greatLearning.GreatLearningAssignment.repository;

import com.greatLearning.GreatLearningAssignment.entity.Loan;
import com.greatLearning.GreatLearningAssignment.entity.User;
import com.greatLearning.GreatLearningAssignment.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {

    List<Loan> findByUser(User user);
    List<Loan> findByStatus(LoanStatus loanStatus);
    List<Loan> findAllByStatusOrderByUser(LoanStatus loanStatus);
}
