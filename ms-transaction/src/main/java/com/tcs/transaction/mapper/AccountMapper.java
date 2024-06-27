package com.tcs.transaction.mapper;

import com.tcs.transaction.dto.AccountDto;
import com.tcs.transaction.dto.AccountResponseDto;
import com.tcs.transaction.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    AccountResponseDto toResponseDto(Account account);
    Account toAccount(AccountDto accountDto);
}
