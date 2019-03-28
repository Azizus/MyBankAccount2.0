package com.kata.bankaccount.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	private long accountId;
	private int balance;
	
	public Account() {
		
	}
	public Account(long accountId, int balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	

}
