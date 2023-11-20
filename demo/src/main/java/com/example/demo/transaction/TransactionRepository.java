package com.example.demo.transaction;

import com.example.demo.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository
  extends JpaRepository<Transaction, Long> {
  @Query("SELECT t FROM Transaction t WHERE t.user = ?1")
  Optional<Transaction> findTransactionByUserId(String email);
}
