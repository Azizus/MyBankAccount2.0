package com.kata.bankaccount.ServiceImpl;

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

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.repository.AccountRepository;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import com.kata.bankaccount.service.impl.TransactionPrinter;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	private AccountServiceImpl accountServiceImpl;
	@Mock TransactionRepository transactionRepo;
	@Mock TransactionPrinter transactionPrinter;
	@Mock AccountRepository accountRepo;
	@Before
	public void initialize(){
		accountServiceImpl = new AccountServiceImpl(transactionRepo, transactionPrinter, accountRepo );
	}

	
	@Test
	public void should_deposit_money_in_account() {
		int amount = 100;
		Account account = new Account(1, 0);
		Date date = new Date();

		Transaction transaction = new Transaction(TransactionType.DEPOSIT, date, amount, account.getBalance() + amount);

		when(transactionRepo.save(transaction)).thenReturn(transaction);
		when(accountRepo.findByAccountId(transaction.getAccountId())).thenReturn(account);
				
		accountServiceImpl.deposit(transaction);
				
		verify(accountRepo).save(account);
	}

	@Test 
	public void should_store_withdrawal_transaction(){
		Date date = new Date();
				
		accountServiceImpl.withdraw(TransactionType.WITHDRAWAL, date, 70, 30);
		
		verify(transactionRepo).addWithdrawal(TransactionType.WITHDRAWAL,date , 70, 30);
	}
	
	@Test
	public void should_print_statement() {
		List<Transaction> transactions = Arrays.asList(new Transaction());
		
		given(transactionRepo.findAll()).willReturn(transactions);
		
		accountServiceImpl.printStatement();
		
		verify(transactionPrinter).printLines(transactions);
	}
	
}
