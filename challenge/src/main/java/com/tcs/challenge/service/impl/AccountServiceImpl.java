package com.tcs.challenge.service.impl;

import com.tcs.challenge.dto.AccountDto;
import com.tcs.challenge.dto.AccountResponseDto;
import com.tcs.challenge.entity.Account;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.mapper.AccountMapper;
import com.tcs.challenge.repository.AccountRepository;
import com.tcs.challenge.service.AccountService;
import com.tcs.challenge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

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
            clientService.findById(requestAccount.getClientId());
            Account account = accountRepository.save(accountMapper.toAccount(requestAccount));
            return accountMapper.toResponseDto(account);
        } catch (Exception ex){
            throw new GeneralException(ex, ex.getMessage());
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
        if (!accountRepository.existsById(id)) throw new GeneralException("Account not found with id: " + id);
        accountRepository.inactivateAccount(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponseDto findById(Long id) throws GeneralException {
        Optional<Account> accountFind = accountRepository.findById(id);
        if (accountFind.isEmpty()) throw new GeneralException("Account not found with id: " + id);
        return accountMapper.toResponseDto(accountFind.get());
    }
}
