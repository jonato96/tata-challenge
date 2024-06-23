package com.tcs.challenge.controller;

import com.tcs.challenge.dto.ClientDto;
import com.tcs.challenge.dto.ClientResponseDto;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.service.ClientService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable("id") Long id) throws GeneralException {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@RequestBody ClientDto clientDto) throws GeneralException {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @PutMapping
    public ResponseEntity<ClientResponseDto> edit(@RequestBody ClientDto clientDto) throws GeneralException {
        return ResponseEntity.ok(clientService.edit(clientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivate(@PathVariable("id") Long id) throws GeneralException {
        clientService.delete(id);
        return ResponseEntity.ok("Client with id: " + id + " has been inactivated");
    }

}
