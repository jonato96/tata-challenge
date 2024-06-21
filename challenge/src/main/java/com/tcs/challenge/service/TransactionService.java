package com.tcs.challenge.service;

import com.tcs.challenge.dto.TransactionDto;
import com.tcs.challenge.dto.TransactionResponseDto;
import com.tcs.challenge.exception.GeneralException;

public interface TransactionService {

    TransactionResponseDto save(TransactionDto requestMovement) throws GeneralException;
    void delete(Long id) throws GeneralException;
    TransactionResponseDto findById(Long id) throws GeneralException;

}
