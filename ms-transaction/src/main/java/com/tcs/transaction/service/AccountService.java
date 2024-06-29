package com.tcs.transaction.service;

import com.tcs.transaction.dto.AccountDto;
import com.tcs.transaction.dto.AccountResponseDto;
import com.tcs.transaction.entity.Account;
import com.tcs.transaction.exception.GeneralException;

import java.math.BigDecimal;

public interface AccountService {
    AccountResponseDto save(AccountDto requestAccount) throws GeneralException;
    void updateBalance(BigDecimal actualBalance, Long id);
    void delete(Long id) throws GeneralException;
    AccountResponseDto findById(Long id) throws GeneralException;
    Account validateAccount(Long id) throws GeneralException;
}
