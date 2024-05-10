package com.memms.melodicle.domain.vo;

import com.memms.melodicle.domain.entities.PlaylistEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private Long userId;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String fname;

    @NotBlank
    @Size(min = 2, max = 30)
    private String lname;

    @Email
    private String email;

    //TODO: Refactor to Playlist class
    private List<PlaylistEntity> playlist;
}
