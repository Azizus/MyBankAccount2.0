package com.kata.bankaccount.exceptions;

public class TransactionException extends Exception{

	private static final long serialVersionUID = 1L;

	public TransactionException(String message) {
		super(message);
	}
}
