package com.kata.bankaccount.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kata.bankaccount.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  public Transaction save(Transaction transaction);

  // @Query(value = "SELECT * FROM Transaction WHERE accountId = ?0")
  public List<Transaction> findAllByAccountId(long accountId);

}
