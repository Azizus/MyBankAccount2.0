package com.kata.bankaccount.test;

import static org.mockito.Mockito.verify;

import java.util.Arrays;
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
		Date date = new Date();
		accountServiceImpl.deposit(100, date, TransactionType.DEPOSIT, 100);
		
		verify(transactionRepo).addDeposit(100, date, TransactionType.DEPOSIT, 100);
	}

	@Test 
	public void should_store_withdrawal_transaction() {
		Date date = new Date();
		accountServiceImpl.withdraw(70, date, TransactionType.DEPOSIT, 30);
		
		verify(transactionRepo).addWithdrawal(70, date, TransactionType.DEPOSIT, 30);
	}
	
	@Test
	public void should_print_statement() {
		List<Transaction> transactions = Arrays.asList(new Transaction());
		
		given(transactionRepo.findAll()).willReturn(transactions);
		
		accountServiceImpl.printStatement();
		
		verify(transactionPrinter).printLines(transactions);
	}
}