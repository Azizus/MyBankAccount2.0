package com.kata.bankaccount.domain;

import java.io.Serializable;
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
public class Account implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public boolean hasBalanceAbove(int amount) {
    return getBalance() < amount;
  }

  @Id
  private long accountId;
  private int balance;

}
