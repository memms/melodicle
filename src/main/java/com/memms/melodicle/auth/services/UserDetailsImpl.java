package com.memms.melodicle.auth.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.memms.melodicle.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class UserDetailsImpl implements UserDetails {

    @Getter
    private Long id;

    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(UserEntity user) {
//        List<GrantedAuthority> authorities = user.getRoles()
//                .stream()
//                .map(role -> role.getName().name)
//        .collect(Collectors.toList());

        log.info(user.toString());


        List<GrantedAuthority> authorities  = user.getRoles()
                     .stream()
                     .flatMap(role -> role.getRoleName().getAuthorities().stream())
                     .collect(Collectors.toUnmodifiableList());


//        List<GrantedAuthority> authorities = user.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
//                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPasswordHash(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){return true;}
        if(obj == null || obj.getClass() != getClass()){return false;}
        UserDetailsImpl user = (UserDetailsImpl) obj;
        return Objects.equals(id, user.id);
    }
}
