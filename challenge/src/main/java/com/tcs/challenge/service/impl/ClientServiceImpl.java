package com.tcs.challenge.service.impl;

import com.tcs.challenge.dto.ClientDto;
import com.tcs.challenge.entity.Client;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.mapper.ClientMapper;
import com.tcs.challenge.repository.ClientRepository;
import com.tcs.challenge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientDto save(ClientDto requestClient) throws GeneralException {
        try {
            Client client = clientRepository.save(clientMapper.toClient(requestClient));
            return clientMapper.toClientDto(client);
        } catch (Exception ex){
            throw new GeneralException(ex, ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws GeneralException {
        if (!clientRepository.existsById(id)) throw new GeneralException("Client not found with id: " + id);
        clientRepository.inactivateClient(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto findById(Long id) throws GeneralException {
        Optional<Client> clientFind = clientRepository.findById(id);
        if (clientFind.isEmpty()) throw new GeneralException("Client not found with id: " + id);
        return clientMapper.toClientDto(clientFind.get());
    }
}
