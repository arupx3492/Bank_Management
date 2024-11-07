package com.greatLearning.GreatLearningAssignment.repository;

import com.greatLearning.GreatLearningAssignment.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Integer> {

    Token findByToken(String token);
}
