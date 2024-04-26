package com.memms.melodicle.controller;

import com.memms.melodicle.auth.jwt.JwtTokenProvider;
import com.memms.melodicle.domain.dto.AuthRequest;
import com.memms.melodicle.domain.dto.AuthResponse;
import com.memms.melodicle.domain.dto.UserDTO;
import com.memms.melodicle.domain.vo.User;
import com.memms.melodicle.repository.RoleRepository;
import com.memms.melodicle.repository.UserRepository;
import com.memms.melodicle.services.AuthService;
import com.memms.melodicle.services.UserService;
import com.memms.melodicle.utility.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Operation(summary = "Create a new user.")
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Validated @RequestBody User user) {
        System.out.println("user: " + user);
        authService.register(user);
        return ResponseEntity.accepted().build();
//        User createdUser = authService.register(user);
//        UserDTO createdUserDTO = UserUtil.convertToDTO(createdUser);
//        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Authenticate a user")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @Validated @RequestBody AuthRequest request
            ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
