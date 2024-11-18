package com.Bank.repository;

import com.Bank.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Integer> {

    Token findByToken(String token);
}
