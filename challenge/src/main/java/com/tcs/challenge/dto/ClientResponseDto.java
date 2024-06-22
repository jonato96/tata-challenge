package com.tcs.challenge.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponseDto {

    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private boolean status;

}
