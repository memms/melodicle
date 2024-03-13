package com.memms.melodicle.utility;

import com.memms.melodicle.domain.dto.UserDTO;
import com.memms.melodicle.domain.entities.UserEntity;
import com.memms.melodicle.domain.vo.User;

public class UserUtil {

    public static UserEntity convertToEntity(User user) {
        if(user == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPasswordHash(user.getPasswordHash());
        userEntity.setFname(user.getFname());
        userEntity.setLname(user.getLname());
        userEntity.setEmail(user.getEmail());
        userEntity.setPlaylist(user.getPlaylist());
        return userEntity;
    }

    public static User convertToVO(UserEntity userEntity) {
        if(userEntity == null) {
            return null;
        }
        return new User(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getPasswordHash(),
                userEntity.getFname(),
                userEntity.getLname(),
                userEntity.getEmail(),
                userEntity.getPlaylist()
        );
    }

    public static UserDTO convertToDTO(User user) {
        if(user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFname(user.getFname());
        userDTO.setLname(user.getLname());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
