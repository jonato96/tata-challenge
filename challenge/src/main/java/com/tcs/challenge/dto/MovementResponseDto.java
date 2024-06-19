package com.tcs.challenge.dto;

import com.tcs.challenge.helper.MovementType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class MovementResponseDto {
    private LocalDate date;
    private MovementType movementType;
    private BigDecimal amount;
    private BigDecimal balance;
    private boolean status;
}
