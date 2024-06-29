package com.tcs.transaction.util;

import com.tcs.transaction.client.CustomerClient;
import com.tcs.transaction.dto.ReportResponseDto;
import com.tcs.transaction.dto.client.ClientResponseDto;
import com.tcs.transaction.entity.Account;
import com.tcs.transaction.entity.Transaction;
import com.tcs.transaction.exception.GeneralException;
import com.tcs.transaction.mapper.TransactionMapper;
import com.tcs.transaction.service.AccountService;
import com.tcs.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final CustomerClient customerClient;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public List<ReportResponseDto> generateReport(Long clientId, LocalDate startDate, LocalDate endData) throws GeneralException {

        ClientResponseDto clientFind = customerClient.findById(clientId);
        if(!clientFind.isStatus()) throw new GeneralException("Client is not active");

        List<Account> accounts = accountService.findByClientId(clientId);
        List<ReportResponseDto> reportResponse = new ArrayList<>();

        for(Account account: accounts) {
            List<Transaction> transactions = transactionService.findByAccountAndDates(account.getId(), startDate, endData);
            ReportResponseDto result = ReportResponseDto.builder()
                    .client(clientFind.getName())
                    .accountNumber(account.getAccountNumber())
                    .accountType(account.getType().toString())
                    .balance(account.getBalance())
                    .transactionDtoList(
                            transactionMapper.toReportDto(transactions)
                    )
                    .build();
            reportResponse.add(result);
        }

        return reportResponse;

    }

}
