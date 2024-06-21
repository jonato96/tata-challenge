package com.tcs.challenge.dto;

import com.tcs.challenge.helper.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {
    private TransactionType transactionType;
    private BigDecimal amount;
    private Long accountId;
    private boolean status;
}
