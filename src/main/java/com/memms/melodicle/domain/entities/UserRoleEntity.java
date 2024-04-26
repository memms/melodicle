package com.memms.melodicle.domain.entities;

import com.memms.melodicle.domain.security.RoleNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auth_roles")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)    //Store Enum as String in DB
    @Column(length = 20)    // Max 20 characters
    private RoleNames roleName;

}
