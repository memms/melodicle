package com.memms.melodicle.services.impl;

import com.memms.melodicle.auth.jwt.JwtTokenProvider;
import com.memms.melodicle.domain.dto.AuthRequest;
import com.memms.melodicle.domain.dto.AuthResponse;
import com.memms.melodicle.domain.entities.UserEntity;
import com.memms.melodicle.domain.entities.UserRoleEntity;
import com.memms.melodicle.domain.security.RoleNames;
import com.memms.melodicle.domain.vo.User;
import com.memms.melodicle.repository.RoleRepository;
import com.memms.melodicle.repository.UserRepository;
import com.memms.melodicle.services.AuthService;
import com.memms.melodicle.utility.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public void register(User user) {
        UserRoleEntity roleEntity = roleRepository.findByRoleName(RoleNames.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("Role User was not initiated in DB"));

//        userRepository.findByUsername(user.getUsername())

        UserEntity userEntity = UserUtil.convertToEntity(user);
        userEntity.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(Set.of(roleEntity));
        userRepository.save(userEntity);
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

        log.info(authenticate.toString());
        String authToken = jwtTokenProvider.generateToken(authenticate);
        return AuthResponse.builder()
                .accessToken(authToken)
                .build();
    }
}
