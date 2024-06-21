package com.tcs.challenge.mapper;

import com.tcs.challenge.dto.TransactionDto;
import com.tcs.challenge.dto.TransactionResponseDto;
import com.tcs.challenge.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponseDto toResponseDto(Transaction transaction) {
        return TransactionResponseDto
                .builder()
                .date(transaction.getDate())
                .transactionType(transaction.getType())
                .amount(transaction.getAmount())
                .balance(transaction.getAfterBalance())
                .build();

    }
    public Transaction toMovement(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setAccountId(transactionDto.getAccountId());
        return transaction;
    }
}
