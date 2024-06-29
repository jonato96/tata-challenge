package com.tcs.transaction.controller;

import com.tcs.transaction.dto.ReportResponseDto;
import com.tcs.transaction.dto.TransactionDto;
import com.tcs.transaction.dto.TransactionResponseDto;
import com.tcs.transaction.exception.GeneralException;
import com.tcs.transaction.service.TransactionService;
import com.tcs.transaction.util.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final ReportService reportService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> findById(@PathVariable("id") Long id) throws GeneralException {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<TransactionResponseDto>> findByAndAccount(
            @RequestParam("accountNumber") String accountNumber) throws GeneralException {
        return ResponseEntity.ok(transactionService.findByAccount(accountNumber));
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDto> create(@RequestBody TransactionDto transactionDto) throws GeneralException {
        return ResponseEntity.ok(transactionService.save(transactionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivate(@PathVariable("id") Long id) throws GeneralException {
        transactionService.delete(id);
        return ResponseEntity.ok("Transaction with id: " + id + " has been inactivated");
    }

    @GetMapping("report")
    public ResponseEntity<List<ReportResponseDto>> generateReport(
            @RequestParam("clientId") Long clientId,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) throws GeneralException {

        return ResponseEntity.ok(reportService.generateReport(clientId, startDate, endDate));

    }

}
