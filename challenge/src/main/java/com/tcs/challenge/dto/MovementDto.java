package com.tcs.challenge.dto;

import com.tcs.challenge.helper.MovementType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovementDto {
    private MovementType movementType;
    private BigDecimal amount;
    private Long accountId;
    private boolean status;
}
