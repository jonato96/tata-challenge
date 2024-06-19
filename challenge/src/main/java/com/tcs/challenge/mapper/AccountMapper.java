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
                .accountType(account.getAccountType())
                .status(account.isStatus())
                .initialBalance(account.getInitialBalance())
                .build();
    }

    public Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setAccountType(accountDto.getAccountType());
        account.setStatus(accountDto.isStatus());
        account.setInitialBalance(accountDto.getInitialBalance());
        account.setClientId(accountDto.getClientId());
        account.setAccountNumber(accountDto.getAccountNumber());
        return account;
    }



}
