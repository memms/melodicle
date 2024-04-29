package com.memms.melodicle.domain.dto;

import com.memms.melodicle.domain.security.RoleNames;
import lombok.*;

import java.util.Set;

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

    private Set<RoleNames> roles;

}
