package com.tcs.challenge.mapper;

import com.tcs.challenge.dto.MovementDto;
import com.tcs.challenge.dto.MovementResponseDto;
import com.tcs.challenge.entity.Movement;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MovementMapper {

    public MovementResponseDto toResponseDto(Movement movement) {
        return MovementResponseDto
                .builder()
                .date(movement.getDate())
                .movementType(movement.getMovementType())
                .amount(movement.getAmount())
                .balance(movement.getBalance())
                .status(movement.isStatus())
                .build();

    }
    public Movement toMovement(MovementDto movementDto) {
        Movement movement = new Movement();
        movement.setMovementType(movementDto.getMovementType());
        movement.setAmount(movementDto.getAmount());
        movement.setAccountId(movementDto.getAccountId());
        movement.setStatus(movementDto.isStatus());
        return movement;
    }
}
