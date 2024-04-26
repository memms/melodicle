package com.memms.melodicle.domain.dto;

import com.memms.melodicle.domain.entities.PlaylistEntity;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;

    private String username;

    private String fname;

    private String lname;

    private String email;

    private String password;

}
