package com.tcs.challenge.service;
import com.tcs.challenge.dto.ClientDto;
import com.tcs.challenge.dto.ClientResponseDto;
import com.tcs.challenge.exception.GeneralException;

public interface ClientService {

    ClientResponseDto save(ClientDto requestClient) throws GeneralException;
    void delete(Long id) throws GeneralException;
    ClientResponseDto findById(Long id) throws GeneralException;

}
