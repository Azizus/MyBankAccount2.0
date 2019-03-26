package com.kata.bankaccount.service;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
	
	public void deposit(int amount);
	
	public void withdraw(int amount);
	
	public void printStatement();

}
