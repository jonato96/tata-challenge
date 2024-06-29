package com.tcs.transaction.mapper;

import com.tcs.transaction.dto.ReportTransactionDto;
import com.tcs.transaction.dto.TransactionDto;
import com.tcs.transaction.dto.TransactionResponseDto;
import com.tcs.transaction.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {

    TransactionResponseDto toResponseDto(Transaction transaction);
    List<TransactionResponseDto> toListResponseDto(List<Transaction> transactionList);
    Transaction toTransaction(TransactionDto transactionDto);
    List<ReportTransactionDto> toReportDto (List<Transaction> transaction);

}
