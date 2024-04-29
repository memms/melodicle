package com.memms.melodicle.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.memms.melodicle.domain.dto.UserDTO;
import com.memms.melodicle.domain.entities.UserEntity;
import com.memms.melodicle.domain.vo.User;
import com.memms.melodicle.exceptions.UserNotFoundException;
import com.memms.melodicle.repository.UserRepository;
import com.memms.melodicle.services.AdminService;
import com.memms.melodicle.utility.UserUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Override
    public UserDTO applyPatchToUser(Long uid, JsonPatch jsonPatch) throws JsonProcessingException, JsonPatchException {

        UserEntity userEntity = getUserEntityById(uid);

        JsonNode patched = jsonPatch.apply(jacksonObjectMapper.convertValue(userEntity, JsonNode.class));

        UserEntity patchedUserEntity = jacksonObjectMapper.treeToValue(patched, UserEntity.class);

        userRepository.save(patchedUserEntity);

        return UserUtil.convertToDTO(patchedUserEntity);
    }

    @Override
    public UserDTO getUserById(Long uid) {
        UserEntity userEntity = getUserEntityById(uid);
        return UserUtil.convertToDTO(userEntity);
    }

    @Override
    public void deleteUserById(Long uid) {
        UserEntity userEntity = getUserEntityById(uid);
        userRepository.delete(userEntity);
    }

    @Override
    public void updateUserById(Long uid, User user) {
        UserEntity userEntity = getUserEntityById(uid);
        if(userEntity.getUsername()!=user.getUsername()){
            Optional<UserEntity> userEntityOptional1 = userRepository.findByUsername(user.getUsername());
            if(userEntityOptional1.isPresent()){
                throw new EntityExistsException("Username " + user.getUsername() + " already exists");
            }
            userEntity.setUsername(user.getUsername());
        }
        userEntity.setLname(user.getLname());
        userEntity.setFname(user.getFname());
        userEntity.setEmail(user.getEmail());
        userRepository.save(userEntity);
    }


    private UserEntity getUserEntityById(Long uid){
        return userRepository.findById(uid)
                .orElseThrow(() -> new EntityNotFoundException("user with id " + uid + " not found"));

    }

}
