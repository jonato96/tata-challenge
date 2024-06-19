package com.tcs.challenge.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class AccountResponseDto {
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private boolean status;
}
