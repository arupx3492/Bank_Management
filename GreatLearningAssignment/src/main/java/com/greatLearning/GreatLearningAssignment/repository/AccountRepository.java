package com.greatLearning.GreatLearningAssignment.repository;

import com.greatLearning.GreatLearningAssignment.entity.Account;
import com.greatLearning.GreatLearningAssignment.entity.User;
import com.greatLearning.GreatLearningAssignment.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

Optional<Account> findByAccountNumber(String accountNo);

Optional<Account> findByUserAndType(User user, AccountType accountType);


}
