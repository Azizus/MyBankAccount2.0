package com.kata.bankaccount.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;

public class TransactionServiceTest {

	private TransactionServiceImpl transactionServiceImpl;
	@Mock TransactionRepository transactionRepo;
	@Before
	public void initialize(){
		transactionServiceImpl = new TransactionServiceImpl();
	}

	@Test
	public void should_return_all_transactions() {
		transactionServiceImpl.allTransactions();
		
		verify(transactionRepo).findAll();
	}

}
