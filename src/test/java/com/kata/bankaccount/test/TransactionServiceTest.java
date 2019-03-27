package com.kata.bankaccount.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.BDDMockito.*;


import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	private TransactionServiceImpl transactionServiceImpl;
	@Mock TransactionRepository transactionRepo;
	@Mock AccountServiceImpl accountServiceImpl;
	
	@Before
	public void initialize(){
		transactionServiceImpl = new TransactionServiceImpl(transactionRepo);
	}

	@Test
	public void should_return_all_transactions() {
		transactionServiceImpl.allTransactions();
		
		verify(transactionRepo).findAll();
	}

	@Test
	public void should_return_balance_of_last_transaction() {
		List<Transaction> transactions = new ArrayList<Transaction>();		
		Transaction transaction = new Transaction(TransactionType.DEPOSIT, new Date(), 100, 100);
		transactions.add(transaction);
		
		assertThat(transactionServiceImpl.getBalanceOfLastTransaction(transactions), is(equalTo(100)));
	}
	
}
