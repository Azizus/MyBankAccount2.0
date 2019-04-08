package com.kata.bankaccount.domain;

import javax.persistence.Entity;
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
public class Account {

  public boolean hasBalanceAbove(int amount) {
    return getBalance() < amount;
  }
  @Id
  private long accountId;
  private int balance;

}
