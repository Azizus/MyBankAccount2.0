package com.kata.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kata.bankaccount.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	


	public void deposit(int amount);
	public void withdraw(int amount);

}
