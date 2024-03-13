package com.memms.melodicle.domain.vo;

import com.memms.melodicle.domain.entities.PlaylistEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private Long userId;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    private String passwordHash;

    @NotNull
    @NotBlank
    private String fname;

    @NotBlank
    private String lname;

    @Email
    private String email;

    private List<PlaylistEntity> playlist;
}
