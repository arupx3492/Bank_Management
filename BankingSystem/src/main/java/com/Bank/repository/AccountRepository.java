package com.Bank.repository;

import com.Bank.entity.Account;
import com.Bank.entity.User;
import com.Bank.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

Optional<Account> findByAccountNumber(String accountNo);

Optional<Account> findByUserAndType(User user, AccountType accountType);


}
