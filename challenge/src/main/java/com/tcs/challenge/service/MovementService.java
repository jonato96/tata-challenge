package com.tcs.challenge.service;

import com.tcs.challenge.dto.MovementDto;
import com.tcs.challenge.dto.MovementResponseDto;
import com.tcs.challenge.exception.GeneralException;

public interface MovementService {

    MovementResponseDto save(MovementDto requestMovement) throws GeneralException;
    void delete(Long id) throws GeneralException;
    MovementResponseDto findById(Long id) throws GeneralException;

}
