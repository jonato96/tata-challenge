package com.tcs.challenge.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;
    private Long clientId;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private boolean status;
}
