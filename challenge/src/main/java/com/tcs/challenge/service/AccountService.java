package com.tcs.challenge.service;

import com.tcs.challenge.dto.AccountDto;
import com.tcs.challenge.dto.AccountResponseDto;
import com.tcs.challenge.entity.Account;
import com.tcs.challenge.exception.GeneralException;

import java.math.BigDecimal;

public interface AccountService {

    AccountResponseDto save(AccountDto requestAccount) throws GeneralException;
    void updateBalance(BigDecimal actualBalance, Long id);
    void delete(Long id) throws GeneralException;
    AccountResponseDto findById(Long id) throws GeneralException;

}
