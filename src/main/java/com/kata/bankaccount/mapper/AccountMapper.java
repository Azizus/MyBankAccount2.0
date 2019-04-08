package com.kata.bankaccount.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.dto.AccountDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AccountMapper {

  public AccountDto accountToAccountDto(Account account) {
    AccountDto dto = AccountDto.builder()//
        .accountId(account.getAccountId())//
        .balance(account.getBalance())//
        .build();
    return dto;
  }

  public List<AccountDto> accountListToAccountDtoList(List<Account> accounts) {
    List<AccountDto> accountsDto = new ArrayList<>();
    accounts.stream()//
        .forEach(account -> accountsDto.add(accountToAccountDto(account)));
    return accountsDto;
  }

}
