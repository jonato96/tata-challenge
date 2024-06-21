package com.tcs.challenge.mapper;

import com.tcs.challenge.dto.AccountDto;
import com.tcs.challenge.dto.AccountResponseDto;
import com.tcs.challenge.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDto toResponseDto(Account account) {
        return AccountResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getType())
                .status(account.isState())
                .initialBalance(account.getBalance())
                .build();
    }

    public Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setType(accountDto.getAccountType());
        account.setState(accountDto.isStatus());
        account.setBalance(accountDto.getInitialBalance());
        account.setClientId(accountDto.getClientId());
        account.setAccountNumber(accountDto.getAccountNumber());
        return account;
    }



}
