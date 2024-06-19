package com.tcs.challenge.service.impl;

import com.tcs.challenge.dto.AccountResponseDto;
import com.tcs.challenge.dto.MovementDto;
import com.tcs.challenge.dto.MovementResponseDto;
import com.tcs.challenge.entity.Movement;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.helper.MovementType;
import com.tcs.challenge.mapper.MovementMapper;
import com.tcs.challenge.repository.MovementsRepository;
import com.tcs.challenge.service.AccountService;
import com.tcs.challenge.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementsRepository movementsRepository;
    private final MovementMapper movementMapper;
    private final AccountService accountService;

    @Override
    @Transactional
    public MovementResponseDto save (MovementDto movementRequest) throws GeneralException {
        try {

            AccountResponseDto accountFind =  accountService.findById(movementRequest.getAccountId());
            movementRequest.setAmount(movementRequest.getAmount().compareTo(BigDecimal.ZERO) < 0 ? movementRequest.getAmount().abs() : movementRequest.getAmount());
            Movement movement = new Movement();
            BigDecimal actualBalance = BigDecimal.ZERO;
            if (movementRequest.getMovementType().equals(MovementType.DEBIT)
                    && accountFind.getInitialBalance().doubleValue() < movementRequest.getAmount().doubleValue()) {
                throw new GeneralException("Account Balance is not sufficient");
            }
            if (movementRequest.getMovementType().equals(MovementType.DEBIT)) {
                actualBalance = accountFind.getInitialBalance().subtract(movementRequest.getAmount());
                movement = movementMapper.toMovement(movementRequest);
                movement.setBalance(actualBalance);
            }
            if (movementRequest.getMovementType().equals(MovementType.CREDIT)) {
                actualBalance = accountFind.getInitialBalance().add(movementRequest.getAmount());
                movement = movementMapper.toMovement(movementRequest);
                movement.setBalance(actualBalance);
            }
            movement.setDate(LocalDate.now());
            accountService.updateBalance(actualBalance, movementRequest.getAccountId());
            return movementMapper.toResponseDto(movementsRepository.save(movement));
        } catch (Exception ex){
            throw new GeneralException(ex, ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws GeneralException {
        if (!movementsRepository.existsById(id)) throw new GeneralException("Movement not found with id: " + id);
        movementsRepository.inactivateMovement(id);
    }

    @Override
    @Transactional(readOnly = true)
    public MovementResponseDto findById(Long id) throws GeneralException {
        Optional<Movement> movementFind = movementsRepository.findById(id);
        if (movementFind.isEmpty()) throw new GeneralException("Movement not found with id: " + id);
        return movementMapper.toResponseDto(movementFind.get());
    }
}
