package com.kata.bankaccount.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction {
	@Id
	private long transactionId;
	private long accountId;
	private Date date;
	private int amount;
	private int balance;
	private TransactionType type;

	public Transaction() {
		
	}
	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}
	public Transaction(Date date, int amount, int balance, TransactionType type) {
		super();
		this.date = date;
		this.amount = amount;
		this.balance = balance;
		this.type = type;
	}

	

}
