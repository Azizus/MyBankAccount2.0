package com.kata.bankaccount.service.impl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.BDDMockito.*;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import com.kata.bankaccount.service.impl.TransactionPrinter;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	private AccountServiceImpl accountServiceImpl;
	@Mock TransactionRepository transactionRepo;
	@Mock TransactionPrinter transactionPrinter;
	@Before
	public void initialize(){
		accountServiceImpl = new AccountServiceImpl(transactionRepo, transactionPrinter);
	}

	@Test
	public void should_store_deposit_transaction() {
		accountServiceImpl.deposit(100);
		
		verify(transactionRepo).deposit(100);
	}

	@Test 
	public void should_store_withdrawal_transaction() {
		accountServiceImpl.withdraw(100);
		
		verify(transactionRepo).withdraw(100);
	}
	
	@Test
	public void should_print_statement() {
		List<Transaction> transactions = Arrays.asList(new Transaction());
		
		given(transactionRepo.findAll()).willReturn(transactions);
		
		accountServiceImpl.printStatement();
		
		verify(transactionPrinter).print(transactions);
	}
}
