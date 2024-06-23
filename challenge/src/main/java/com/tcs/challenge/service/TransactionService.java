package com.tcs.challenge.service;

import com.tcs.challenge.dto.TransactionDto;
import com.tcs.challenge.dto.TransactionResponseDto;
import com.tcs.challenge.exception.GeneralException;

import java.util.List;

public interface TransactionService {

    TransactionResponseDto save(TransactionDto requestMovement) throws GeneralException;
    void delete(Long id) throws GeneralException;
    TransactionResponseDto findById(Long id) throws GeneralException;
    List<TransactionResponseDto> findByAccount(String account) throws GeneralException;

}
