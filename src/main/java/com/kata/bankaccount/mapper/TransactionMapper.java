package com.kata.bankaccount.mapper;

import java.util.ArrayList;
import java.util.List;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.dto.TransactionDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionMapper {



  public static TransactionDto transactionToTransactionDto(Transaction transaction) {
    TransactionDto dto = TransactionDto.builder()//
        .accountId(transaction.getAccountId()).amount(transaction.getAmount())//
        .date(transaction.getDate())//
        .balance(transaction.getBalance()).transactionId(transaction.getTransactionId())//
        .type(transaction.getType())//
        .build();

    return dto;
  }

  public static List<TransactionDto> transactionListToTransactionDtoList(
      List<Transaction> transactions) {
    List<TransactionDto> transactionsDto = new ArrayList<>();
    transactions.stream()//
        .forEach(transaction -> transactionsDto.add(transactionToTransactionDto(transaction)));
    return transactionsDto;
  }



}
