package com.tcs.challenge.mapper;

import com.tcs.challenge.dto.AccountDto;
import com.tcs.challenge.dto.AccountResponseDto;
import com.tcs.challenge.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    @Mapping(source = "client.name", target = "clientName")
    AccountResponseDto toResponseDto(Account account);
    Account toAccount(AccountDto accountDto);
}
