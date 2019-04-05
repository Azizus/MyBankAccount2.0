package com.kata.bankaccount.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kata.bankaccount.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  // @Query(value = "SELECT * FROM Account")
  public List<Account> findAll();

  // @Query(value = "SELECT * FROM Account WHERE accountId = ?0")
  public Account findByAccountId(long accountId);

  // @Query("delete from Account a where a.accountId = ?0")
  public void deleteByAccountId(long accountId);

}
