package com.tcs.transaction.controller;

import com.tcs.transaction.dto.TransactionDto;
import com.tcs.transaction.dto.TransactionResponseDto;
import com.tcs.transaction.exception.GeneralException;
import com.tcs.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

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
}
