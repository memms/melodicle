package com.memms.melodicle.services;

import com.memms.melodicle.domain.vo.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userID);

    User getUserByUsername(String username);
}
