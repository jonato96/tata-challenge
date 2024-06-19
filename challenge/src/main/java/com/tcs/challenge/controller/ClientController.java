package com.tcs.challenge.controller;

import com.tcs.challenge.dto.ClientDto;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable("id") Long id) throws GeneralException {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto) throws GeneralException {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @PutMapping
    public ResponseEntity<ClientDto> edit(@RequestBody ClientDto clientDto) throws GeneralException {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deactivate(@RequestParam("id") Long id) throws GeneralException {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

}
