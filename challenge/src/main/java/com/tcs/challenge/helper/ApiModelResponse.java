package com.tcs.challenge.helper;

import lombok.Builder;

@Builder
public record ApiModelResponse<T>(
        StatusResponse statusResponse,
        String message,
        T data
) {
}
