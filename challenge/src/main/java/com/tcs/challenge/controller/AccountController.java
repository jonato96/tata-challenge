package com.tcs.challenge.controller;

import com.tcs.challenge.dto.AccountDto;
import com.tcs.challenge.dto.AccountResponseDto;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> findById(@PathVariable("id") Long id) throws GeneralException {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> create(@RequestBody AccountDto accountDto) throws GeneralException {
        return ResponseEntity.ok(accountService.save(accountDto));
    }

    @PutMapping
    public ResponseEntity<AccountResponseDto> edit(@RequestBody AccountDto accountDto) throws GeneralException {
        return ResponseEntity.ok(accountService.save(accountDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deactivate(@RequestParam("id") Long id) throws GeneralException {
        accountService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

}
