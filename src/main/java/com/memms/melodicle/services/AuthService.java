package com.memms.melodicle.services;

import com.memms.melodicle.domain.dto.AuthRequest;
import com.memms.melodicle.domain.dto.AuthResponse;
import com.memms.melodicle.domain.vo.User;

public interface AuthService {

    void register(User user);

    AuthResponse authenticate(AuthRequest authRequest);
}
