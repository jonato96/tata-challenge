package com.tcs.challenge.service;
import com.tcs.challenge.dto.ClientDto;
import com.tcs.challenge.exception.GeneralException;

public interface ClientService {

    ClientDto save(ClientDto requestClient) throws GeneralException;
    void delete(Long id) throws GeneralException;
    ClientDto findById(Long id) throws GeneralException;

}
