package com.kata.bankaccount.ServiceImpl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.BDDMockito.*;


import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	private TransactionServiceImpl transactionServiceImpl;
	@Mock TransactionRepository transactionRepo;
	@Mock AccountServiceImpl accountServiceImpl;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	
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
