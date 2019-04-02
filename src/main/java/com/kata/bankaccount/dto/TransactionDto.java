package com.kata.bankaccount.dto;

import java.util.Date;
import com.kata.bankaccount.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionDto {

  private long transactionId;
  private long accountId;
  private Date date;
  private int amount;
  private int balance;
  private TransactionType type;

}
