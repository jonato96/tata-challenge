package com.tcs.challenge.dto;

import com.tcs.challenge.helper.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class TransactionResponseDto {
    private LocalDate date;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;
    private boolean status;
}
