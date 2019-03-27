package com.kata.bankaccount.test;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import com.kata.bankaccount.service.impl.TransactionPrinter;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;

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
		accountServiceImpl.deposit(TransactionType.DEPOSIT, date, 100, 100);
		
		verify(transactionRepo).addDeposit(TransactionType.DEPOSIT, date, 100, 100);
	}

	@Test 
	public void should_store_withdrawal_transaction() throws TransactionException {
		Date date = new Date();
		accountServiceImpl.withdraw(TransactionType.WITHDRAWAL, date, 70, 30);
		
		verify(transactionRepo).addWithdrawal(TransactionType.WITHDRAWAL, date, 70, 30);
	}
	
	@Test
	public void should_print_statement() {
		List<Transaction> transactions = Arrays.asList(new Transaction());
		
		given(transactionRepo.findAll()).willReturn(transactions);
		
		accountServiceImpl.printStatement();
		
		verify(transactionPrinter).printLines(transactions);
	}
	
	@Test
	public void should_return_balance_of_last_transaction() {
		List<Transaction> transactions = new ArrayList<Transaction>();		
		Transaction transaction = new Transaction(TransactionType.DEPOSIT, new Date(), 100, 100);
		transactions.add(transaction);
		
		assertThat(accountServiceImpl.getBalanceOfLastTransaction(transactions), is(equalTo(100)));
	}
}
