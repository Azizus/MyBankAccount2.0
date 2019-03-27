package com.kata.bankaccount.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.TransactionType;

@Service
public interface AccountService {
	
	public void deposit(int amount, Date date, TransactionType type, int balance);
	
	public void withdraw(int amount, Date date, TransactionType type, int balance);
	
	public void printStatement();

}
