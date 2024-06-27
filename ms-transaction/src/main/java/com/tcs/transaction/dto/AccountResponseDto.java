package com.tcs.transaction.dto;

import com.tcs.transaction.helper.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponseDto {

    private Long id;
    private String accountNumber;
    private AccountType type;
    private BigDecimal balance;
    private boolean status;
    private String clientName;

}
