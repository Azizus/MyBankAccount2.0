package com.kata.bankaccount.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	public void addDeposit(TransactionType type, Date date, int amount, int balance);
	
	public void addWithdrawal(TransactionType type, Date date, int amount, int balance);

}
