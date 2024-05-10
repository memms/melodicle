package com.memms.melodicle.utility;

import com.memms.melodicle.domain.dto.PlaylistDTO;
import com.memms.melodicle.domain.dto.UserDTO;
import com.memms.melodicle.domain.entities.PlaylistEntity;
import com.memms.melodicle.domain.vo.Playlist;

public class PlaylistUtil {

    public static PlaylistEntity convertToEntity(Playlist playlist) {
        if(playlist == null) {
            return null;
        }
        PlaylistEntity playlistEntity= new PlaylistEntity();
        playlistEntity.setName(playlist.getName());
        playlistEntity.setPlaylistImageURL(playlist.getPlaylistImageURL());
        return playlistEntity;
    }
//
//    public static User convertToVO(UserEntity userEntity) {
//        if(userEntity == null) {
//            return null;
//        }
//        return new User(
//                userEntity.getUserId(),
//                userEntity.getUsername(),
//                userEntity.getPasswordHash(),
//                userEntity.getFname(),
//                userEntity.getLname(),
//                userEntity.getEmail(),
//                userEntity.getPlaylist()
//        );
//    }
//
//    public static UserDTO convertToDTO(User user) {
//        if(user == null) {
//            return null;
//        }
//        UserDTO userDTO = new UserDTO();
//        setCommonFields(userDTO, user.getUserId(), user.getUsername(), user.getFname(), user.getLname(), user.getEmail());
//        return userDTO;
//    }

    public static PlaylistDTO convertToDTO(PlaylistEntity playlistEntity){
        if(playlistEntity == null){
            return null;
        }
        PlaylistDTO playlistDTO = new PlaylistDTO();
        setCommonFields(playlistDTO, playlistEntity.getPlaylistId(), playlistEntity.getName(), playlistDTO.getDateCreated(),
                playlistEntity.getSongCount(), playlistDTO.getPlaylistImageURL());
        return playlistDTO;
    }

    private static void setCommonFields(PlaylistDTO playlistDTO, Long id, String name, String dateCreated, Integer songCount, String playlistImageURL) {
        playlistDTO.setPlaylistId(id);
        playlistDTO.setName(name);
        playlistDTO.setDateCreated(dateCreated);
        playlistDTO.setSongCount(songCount);
        playlistDTO.setPlaylistImageURL(playlistImageURL);

    }

}
