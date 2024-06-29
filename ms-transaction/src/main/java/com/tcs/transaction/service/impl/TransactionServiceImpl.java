package com.tcs.transaction.service.impl;

import com.tcs.transaction.dto.TransactionDto;
import com.tcs.transaction.dto.TransactionResponseDto;
import com.tcs.transaction.entity.Account;
import com.tcs.transaction.entity.Transaction;
import com.tcs.transaction.exception.GeneralException;
import com.tcs.transaction.helper.TransactionType;
import com.tcs.transaction.mapper.TransactionMapper;
import com.tcs.transaction.repository.TransactionRepository;
import com.tcs.transaction.service.AccountService;
import com.tcs.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;

    @Override
    @Transactional
    public TransactionResponseDto save (TransactionDto transactionRequest) throws GeneralException {
        try {

            Account accountForTx =  accountService.validateAccount(transactionRequest.getAccountId());
            validatePositiveNegativeValues(transactionRequest, accountForTx.getBalance());

            BigDecimal actualBalance = accountForTx.getBalance().add(transactionRequest.getAmount());
            Transaction transaction = transactionMapper.toTransaction(transactionRequest);
            transaction.setStatus(true);
            transaction.setBeforeBalance(accountForTx.getBalance());
            transaction.setAfterBalance(actualBalance);
            transaction.setDate(LocalDate.now());

            accountService.updateBalance(actualBalance, accountForTx.getId());
            return transactionMapper.toResponseDto(transactionRepository.save(transaction));
        } catch (Exception ex){
            throw new GeneralException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws GeneralException {
        validateExistsAndIsActive(id);
        Transaction txToDeactivate = transactionRepository.findById(id).orElse(new Transaction());
        Account accountToUpdate = txToDeactivate.getAccount();
        BigDecimal restoreBalance = accountToUpdate.getBalance().subtract(txToDeactivate.getAmount());
        accountService.updateBalance(restoreBalance,accountToUpdate.getId());
        transactionRepository.inactivateTransaction(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionResponseDto findById(Long id) throws GeneralException {
        Optional<Transaction> movementFind = transactionRepository.findById(id);
        if (movementFind.isEmpty()) throw new GeneralException("Movement not found with id: " + id);
        return transactionMapper.toResponseDto(movementFind.get());
    }

    @Override
    public List<TransactionResponseDto> findByAccount(String account) throws GeneralException {
        List<Transaction> transactionList = transactionRepository.findAllByAccount(account)
                .orElseThrow( () -> new GeneralException("Transactions with account number: " + account + " not found"));

        return transactionMapper.toListResponseDto(transactionList);

    }

    private void validateExistsAndIsActive(Long id) throws GeneralException {
        boolean result = transactionRepository.existsByIdAndStatusTrue(id);
        if (!result) throw new GeneralException("Transaction not found with id: " + id + ", or is already inactive");
    }

    private void validatePositiveNegativeValues(TransactionDto dto, BigDecimal actualBalance) throws GeneralException {
        String msg = "";
        if (dto.getType().equals(TransactionType.DEBIT) && dto.getAmount().compareTo(BigDecimal.ZERO) > 0) msg = "Debit only can be negative";
        if (dto.getType().equals(TransactionType.CREDIT) && dto.getAmount().compareTo(BigDecimal.ZERO) < 0) msg = "Credit only can be positive";
        if ((dto.getType().equals(TransactionType.DEBIT) || dto.getType().equals(TransactionType.CREDIT)) && dto.getAmount().compareTo(BigDecimal.ZERO) == 0) msg = "Transaction with zero amount is not valid";
        if (dto.getType().equals(TransactionType.DEBIT) && actualBalance.compareTo(dto.getAmount().abs()) < 0) msg = "Account Balance is not sufficient";
        if (!msg.isEmpty()) throw new GeneralException(msg);
    }
}
