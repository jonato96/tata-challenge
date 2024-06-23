package com.tcs.challenge.service.impl;

import com.tcs.challenge.dto.AccountDto;
import com.tcs.challenge.dto.AccountResponseDto;
import com.tcs.challenge.entity.Account;
import com.tcs.challenge.entity.Client;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.mapper.AccountMapper;
import com.tcs.challenge.repository.AccountRepository;
import com.tcs.challenge.service.AccountService;
import com.tcs.challenge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientService clientService;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public AccountResponseDto save(AccountDto requestAccount) throws GeneralException {
        try {
            Client clientForAccount = clientService.validateClient(requestAccount.getClientId());
            Account accountToSave = accountMapper.toAccount(requestAccount);
            accountToSave.setStatus(true);
            accountToSave.setClient(clientForAccount);
            Account accountCreated = accountRepository.save(accountToSave);
            return accountMapper.toResponseDto(accountCreated);
        } catch (Exception ex){
            throw new GeneralException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateBalance(BigDecimal actualBalance, Long id) {
        accountRepository.updateBalance(actualBalance, id);
    }

    @Override
    @Transactional
    public void delete(Long id) throws GeneralException {
        validateExistsAndIsActive(id);
        accountRepository.inactivateAccount(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponseDto findById(Long id) throws GeneralException {
        Account accountFind = accountRepository.findById(id).orElseThrow( () -> new GeneralException("Account not found with id: " + id) );
        accountFind.setClient(accountFind.getClient());
        return accountMapper.toResponseDto(accountFind);
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
