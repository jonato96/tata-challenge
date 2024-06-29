package com.tcs.transaction.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class ReportResponseDto {

    private String client;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private List<ReportTransactionDto> transactionDtoList;

}
