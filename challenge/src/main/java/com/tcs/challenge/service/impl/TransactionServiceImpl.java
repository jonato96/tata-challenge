package com.tcs.challenge.service.impl;

import com.tcs.challenge.dto.AccountResponseDto;
import com.tcs.challenge.dto.TransactionDto;
import com.tcs.challenge.dto.TransactionResponseDto;
import com.tcs.challenge.entity.Transaction;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.helper.TransactionType;
import com.tcs.challenge.mapper.TransactionMapper;
import com.tcs.challenge.repository.TransactionRepository;
import com.tcs.challenge.service.AccountService;
import com.tcs.challenge.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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

            AccountResponseDto accountFind =  accountService.findById(transactionRequest.getAccountId());
            transactionRequest.setAmount(transactionRequest.getAmount().compareTo(BigDecimal.ZERO) < 0 ? transactionRequest.getAmount().abs() : transactionRequest.getAmount());
            Transaction transaction = new Transaction();
            BigDecimal actualBalance = BigDecimal.ZERO;
            if (transactionRequest.getTransactionType().equals(TransactionType.DEBIT)
                    && accountFind.getInitialBalance().doubleValue() < transactionRequest.getAmount().doubleValue()) {
                throw new GeneralException("Account Balance is not sufficient");
            }
            if (transactionRequest.getTransactionType().equals(TransactionType.DEBIT)) {
                actualBalance = accountFind.getInitialBalance().subtract(transactionRequest.getAmount());
                transaction = transactionMapper.toMovement(transactionRequest);
                transaction.setAfterBalance(actualBalance);
            }
            if (transactionRequest.getTransactionType().equals(TransactionType.CREDIT)) {
                actualBalance = accountFind.getInitialBalance().add(transactionRequest.getAmount());
                transaction = transactionMapper.toMovement(transactionRequest);
                transaction.setAfterBalance(actualBalance);
            }
            transaction.setDate(LocalDate.now());
            accountService.updateBalance(actualBalance, transactionRequest.getAccountId());
            return transactionMapper.toResponseDto(transactionRepository.save(transaction));
        } catch (Exception ex){
            throw new GeneralException(ex, ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws GeneralException {
        if (!transactionRepository.existsById(id)) throw new GeneralException("Movement not found with id: " + id);
        transactionRepository.inactivateMovement(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionResponseDto findById(Long id) throws GeneralException {
        Optional<Transaction> movementFind = transactionRepository.findById(id);
        if (movementFind.isEmpty()) throw new GeneralException("Movement not found with id: " + id);
        return transactionMapper.toResponseDto(movementFind.get());
    }
}
