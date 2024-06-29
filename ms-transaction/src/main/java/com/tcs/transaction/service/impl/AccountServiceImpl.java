package com.tcs.transaction.service.impl;

import com.tcs.transaction.client.CustomerClient;
import com.tcs.transaction.dto.AccountDto;
import com.tcs.transaction.dto.AccountResponseDto;
import com.tcs.transaction.dto.client.ClientResponseDto;
import com.tcs.transaction.entity.Account;
import com.tcs.transaction.exception.GeneralException;
import com.tcs.transaction.mapper.AccountMapper;
import com.tcs.transaction.repository.AccountRepository;
import com.tcs.transaction.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CustomerClient customerClient;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponseDto save(AccountDto requestAccount) throws GeneralException {
        try {
            ClientResponseDto clientForAccount = customerClient.findById(requestAccount.getClientId());
            Account accountToSave = accountMapper.toAccount(requestAccount);
            accountToSave.setStatus(true);
            accountToSave.setClientId(clientForAccount.getId());
            Account accountCreated = accountRepository.save(accountToSave);
            AccountResponseDto accountResponse = accountMapper.toResponseDto(accountCreated);
            accountResponse.setClientName(clientForAccount.getName());
            return accountResponse;
        } catch (Exception ex){
            throw new GeneralException(ex.getMessage());
        }
    }

    @Override
    public void updateBalance(BigDecimal actualBalance, Long id) {
        accountRepository.updateBalance(actualBalance, id);
    }

    @Override
    public void delete(Long id) throws GeneralException {
        validateExistsAndIsActive(id);
        accountRepository.inactivateAccount(id);
    }

    @Override
    public AccountResponseDto findById(Long id) throws GeneralException {
        Account accountFind = accountRepository.findById(id).orElseThrow( () -> new GeneralException("Account not found with id: " + id) );
        ClientResponseDto clientResponse = customerClient.findById(accountFind.getId());
        AccountResponseDto accountResponse = accountMapper.toResponseDto(accountFind);
        accountResponse.setClientName(clientResponse.getName());
        return accountResponse;
    }

    @Override
    public Account validateAccount(Long id) throws GeneralException {
        validateExistsAndIsActive(id);
        return accountRepository.findById(id).orElse(null);
    }

    private void validateExistsAndIsActive(Long id) throws GeneralException {
        boolean result = accountRepository.existsByIdAndStatusTrue(id);
        if (!result) throw new GeneralException("Account not found with id: " + id + ", or is already inactive");
    }
}
