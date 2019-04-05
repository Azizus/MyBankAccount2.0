package com.kata.bankaccount.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transaction {
  @Id
  @GeneratedValue
  private long transactionId;
  private long accountId;
  private Date date;
  private int amount;
  private int balance;
  private TransactionType type;

}
