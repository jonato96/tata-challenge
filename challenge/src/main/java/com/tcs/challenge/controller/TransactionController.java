package com.tcs.challenge.controller;

import com.tcs.challenge.dto.TransactionDto;
import com.tcs.challenge.dto.TransactionResponseDto;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> findById(@PathVariable("id") Long id) throws GeneralException {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDto> create(@RequestBody TransactionDto transactionDto) throws GeneralException {
        return ResponseEntity.ok(transactionService.save(transactionDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deactivate(@RequestParam("id") Long id) throws GeneralException {
        transactionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }
}
