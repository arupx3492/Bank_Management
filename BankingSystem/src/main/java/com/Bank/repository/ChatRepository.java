package com.Bank.repository;

import com.Bank.entity.Chat;
import com.Bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {
    List<Chat> findBySenderOrderByTimestamp(User user);
}
