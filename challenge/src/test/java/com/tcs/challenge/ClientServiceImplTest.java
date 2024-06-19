package com.tcs.challenge;

import com.tcs.challenge.dto.ClientDto;
import com.tcs.challenge.entity.Client;
import com.tcs.challenge.exception.GeneralException;
import com.tcs.challenge.mapper.ClientMapper;
import com.tcs.challenge.repository.ClientRepository;
import com.tcs.challenge.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void findByIdTest_ShouldReturnOneClient() throws GeneralException {
        // Mock Data
        Long mockId = 1L;
        Client mockClient = new Client();
        mockClient.setId(1L);
        // Mock Behavior
        Mockito.when(clientRepository.findById(mockId)).thenReturn(Optional.of(mockClient));
        Mockito.when(clientMapper.toClientDto(mockClient)).thenReturn(ClientDto.builder().id(1L).build());
        // Call Service
        ClientDto result = clientService.findById(mockId);
        // Verify Results
        Mockito.verify(clientRepository, Mockito.times(1)).findById(mockId);
        assertEquals(1L, result.getId());

    }

}
