package com.tcs.challenge.dto;

import com.tcs.challenge.helper.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class AccountResponseDto {
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private boolean status;
}
