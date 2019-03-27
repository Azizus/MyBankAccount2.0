package com.kata.bankaccount.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	private TransactionServiceImpl transactionServiceImpl;
	@Mock TransactionRepository transactionRepo;
	@Before
	public void initialize(){
		transactionServiceImpl = new TransactionServiceImpl(transactionRepo);
	}

	@Test
	public void should_return_all_transactions() {
		transactionServiceImpl.allTransactions();
		
		verify(transactionRepo).findAll();
	}

}
