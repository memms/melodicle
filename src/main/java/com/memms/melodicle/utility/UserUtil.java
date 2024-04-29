package com.memms.melodicle.utility;

import com.memms.melodicle.domain.dto.UserDTO;
import com.memms.melodicle.domain.entities.UserEntity;
import com.memms.melodicle.domain.entities.UserRoleEntity;
import com.memms.melodicle.domain.security.RoleNames;
import com.memms.melodicle.domain.vo.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserUtil {

    public static UserEntity convertToEntity(User user) {
        if(user == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPasswordHash(user.getPassword());
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
        setCommonFields(userDTO, user.getUserId(), user.getUsername(), user.getFname(), user.getLname(), user.getEmail());
        return userDTO;
    }

    public static UserDTO convertToDTO(UserEntity userEntity){
        if(userEntity == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        setCommonFields(userDTO, userEntity.getUserId(), userEntity.getUsername(), userEntity.getFname(), userEntity.getLname(), userEntity.getEmail());
        Set<RoleNames> roles = userEntity.getRoles().stream().map(UserRoleEntity::getRoleName).collect(Collectors.toSet());
        userDTO.setRoles(roles);
        return userDTO;
    }

    private static void setCommonFields(UserDTO userDTO, Long userId, String username, String fname, String lname, String email) {
        userDTO.setUserId(userId);
        userDTO.setUsername(username);
        userDTO.setFname(fname);
        userDTO.setLname(lname);
        userDTO.setEmail(email);
    }
}
