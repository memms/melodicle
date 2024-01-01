package com.memms.melodicle.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    
    @JsonProperty("auth_token")
    private String authToken;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
