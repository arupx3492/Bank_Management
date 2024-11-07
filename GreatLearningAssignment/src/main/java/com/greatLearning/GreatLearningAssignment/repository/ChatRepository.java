package com.greatLearning.GreatLearningAssignment.repository;

import com.greatLearning.GreatLearningAssignment.entity.Chat;
import com.greatLearning.GreatLearningAssignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {
    List<Chat> findBySenderOrderByTimestamp(User user);
}
