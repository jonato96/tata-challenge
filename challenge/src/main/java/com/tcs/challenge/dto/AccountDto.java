package com.tcs.challenge.dto;

import com.tcs.challenge.helper.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;
    private Long clientId;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private boolean status;
}
