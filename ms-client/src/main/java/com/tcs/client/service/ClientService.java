package com.tcs.client.service;

import com.tcs.client.dto.ClientDto;
import com.tcs.client.dto.ClientResponseDto;
import com.tcs.client.entity.Client;
import com.tcs.client.exception.GeneralException;

public interface ClientService {

    ClientResponseDto save(ClientDto requestClient) throws GeneralException;
    ClientResponseDto edit(ClientDto requestClient) throws GeneralException;
    void delete(Long id) throws GeneralException;
    ClientResponseDto findById(Long id) throws GeneralException;
    Client validateClient(Long id) throws GeneralException;

}
