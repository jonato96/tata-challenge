package com.tcs.transaction.service;

import com.tcs.transaction.dto.TransactionDto;
import com.tcs.transaction.dto.TransactionResponseDto;
import com.tcs.transaction.exception.GeneralException;

import java.util.List;

public interface TransactionService {

    TransactionResponseDto save(TransactionDto requestMovement) throws GeneralException;
    void delete(Long id) throws GeneralException;
    TransactionResponseDto findById(Long id) throws GeneralException;
    List<TransactionResponseDto> findByAccount(String account) throws GeneralException;

}
