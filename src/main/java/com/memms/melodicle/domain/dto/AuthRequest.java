package com.memms.melodicle.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class AuthRequest {

    @NotEmpty(message = "username is required")
    @NotNull(message = "username is required")
    private String username;

    @NotEmpty
    @NotNull
    private String password;
}
