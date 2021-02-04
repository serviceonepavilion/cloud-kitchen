package com.serviceonepavilion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutheticationResponse {
    private String authenticationToken;
    private Integer username;

    private String refreshToken;
    private Instant expiresAt;
}




