package com.tcs.challenge.dto;

import com.tcs.challenge.helper.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    private Long id;
    private Long clientId;
    private String accountNumber;
    private AccountType type;
    private BigDecimal balance;
    private boolean status;

}
