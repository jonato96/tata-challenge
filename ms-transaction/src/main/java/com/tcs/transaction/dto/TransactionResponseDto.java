package com.tcs.transaction.dto;

import com.tcs.transaction.helper.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionResponseDto {

    private Long id;
    private LocalDate date;
    private TransactionType type;
    private BigDecimal beforeBalance;
    private BigDecimal amount;
    private BigDecimal afterBalance;
    private boolean status;

}
