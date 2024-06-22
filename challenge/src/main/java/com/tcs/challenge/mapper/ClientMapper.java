package com.tcs.challenge.mapper;

import com.tcs.challenge.dto.ClientDto;
import com.tcs.challenge.dto.ClientResponseDto;
import com.tcs.challenge.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientResponseDto toClientDto(Client client){
        return ClientResponseDto.builder()
                .name(client.getName())
                .gender(client.getGender())
                .age(client.getAge())
                .identification(client.getIdentification())
                .address(client.getAddress())
                .phone(client.getPhone())
                .status(client.isStatus())
                .build();
    }

    public Client toClient(ClientDto clientDto){
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setGender(clientDto.getGender());
        client.setAge(clientDto.getAge());
        client.setIdentification(clientDto.getIdentification());
        client.setAddress(clientDto.getAddress());
        client.setPassword(clientDto.getPassword());
        client.setStatus(clientDto.isStatus());
        client.setPhone(clientDto.getPhone());
        return client;
    }

}
