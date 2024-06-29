package com.tcs.transaction.controller;

import com.tcs.transaction.dto.AccountDto;
import com.tcs.transaction.dto.AccountResponseDto;
import com.tcs.transaction.exception.GeneralException;
import com.tcs.transaction.service.AccountService;
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

    @DeleteMapping
    public ResponseEntity<String> deactivate(@RequestParam("id") Long id) throws GeneralException {
        accountService.delete(id);
        return ResponseEntity.ok("Account with id: " + id + " has been inactivated");
    }

}
