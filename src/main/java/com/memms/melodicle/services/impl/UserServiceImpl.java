package com.memms.melodicle.services.impl;

import com.memms.melodicle.domain.entities.UserEntity;
import com.memms.melodicle.domain.vo.User;
import com.memms.melodicle.repository.UserRepository;
import com.memms.melodicle.services.UserService;
import com.memms.melodicle.utility.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User createUser(User user) {
        UserEntity userEntity = userRepository.save(UserUtil.convertToEntity(user));
        return UserUtil.convertToVO(userEntity);
    }

    @Override
    public User getUserById(Long userID) {
        UserEntity userEntity = userRepository.findById(userID).orElse(null);
        return UserUtil.convertToVO(userEntity);
    }

    @Override
    public User getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElse(null);
        return UserUtil.convertToVO(userEntity);
    }
}
