package com.kata.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kata.bankaccount.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


}
