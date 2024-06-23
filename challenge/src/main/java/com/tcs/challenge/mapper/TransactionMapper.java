package com.tcs.challenge.mapper;

import com.tcs.challenge.dto.TransactionDto;
import com.tcs.challenge.dto.TransactionResponseDto;
import com.tcs.challenge.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {

    TransactionResponseDto toResponseDto(Transaction transaction);
    List<TransactionResponseDto> toListResponseDto(List<Transaction> transactionList);
    Transaction toTransaction(TransactionDto transactionDto);

}
