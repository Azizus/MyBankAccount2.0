package com.kata.bankaccount.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
  @Id
  private long transactionId;
  private long accountId;
  private Date date;
  private int amount;
  private int balance;
  private TransactionType type;

  public Transaction(long accountId, Date date, int amount, int balance, TransactionType type) {
    super();
    this.accountId = accountId;
    this.date = date;
    this.amount = amount;
    this.balance = balance;
    this.type = type;
  }



}
