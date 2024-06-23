package com.tcs.challenge.mapper;

import com.tcs.challenge.dto.ClientDto;
import com.tcs.challenge.dto.ClientResponseDto;
import com.tcs.challenge.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestMapper {
    ClientResponseDto toClientDto(Client client);
    Client toClient(ClientDto clientDto);
}
