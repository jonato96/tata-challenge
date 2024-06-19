package com.tcs.challenge.controller;

import com.tcs.challenge.dto.MovementDto;
import com.tcs.challenge.dto.MovementResponseDto;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.service.MovementService;
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
@RequestMapping("api/movement")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @GetMapping("/{id}")
    public ResponseEntity<MovementResponseDto> findById(@PathVariable("id") Long id) throws GeneralException {
        return ResponseEntity.ok(movementService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MovementResponseDto> create(@RequestBody MovementDto movementDto) throws GeneralException {
        return ResponseEntity.ok(movementService.save(movementDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deactivate(@RequestParam("id") Long id) throws GeneralException {
        movementService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }
}
