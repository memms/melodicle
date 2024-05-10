package com.memms.melodicle.services.impl;

import com.memms.melodicle.auth.services.UserDetailsImpl;
import com.memms.melodicle.domain.dto.PlaylistDTO;
import com.memms.melodicle.domain.dto.SongDTO;
import com.memms.melodicle.domain.entities.ArtistEntity;
import com.memms.melodicle.domain.entities.PlaylistEntity;
import com.memms.melodicle.domain.entities.SongEntity;
import com.memms.melodicle.domain.entities.UserEntity;
import com.memms.melodicle.domain.vo.Playlist;
import com.memms.melodicle.domain.vo.Song;
import com.memms.melodicle.repository.ArtistRepository;
import com.memms.melodicle.repository.PlaylistRepository;
import com.memms.melodicle.repository.SongRepository;
import com.memms.melodicle.repository.UserRepository;
import com.memms.melodicle.services.PlaylistService;
import com.memms.melodicle.utility.PlaylistUtil;
import com.memms.melodicle.utility.SongUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SongRepository songRepository;
    @Autowired
    ArtistRepository artistRepository;

    @Override
    public PlaylistDTO getPlaylistMetaById(Long id) {
        PlaylistEntity playlistEntity = getPlaylistEntityById(id);
        PlaylistDTO playlistDTO = PlaylistUtil.convertToDTO(playlistEntity);
        playlistDTO.setUsername(playlistEntity.getUserEntity().getUsername());
        return playlistDTO;
    }

    @Override
    public PlaylistDTO createPlaylist(Playlist playlist, Authentication authentication) {
        PlaylistEntity playlistEntity = PlaylistUtil.convertToEntity(playlist);
        String loggedInUsername = getLoggedInUserName(authentication);
        playlistEntity.setUserEntity(getUserEntityByUsername(loggedInUsername));
        playlistEntity.setDateCreated(String.valueOf(new Date()));
        playlistRepository.save(playlistEntity);
        PlaylistDTO playlistDTO = PlaylistUtil.convertToDTO(playlistEntity);
        playlistDTO.setUsername(loggedInUsername);
        return playlistDTO;
    }

//    Delete playlist if owned by a user by playlist id
    @Override
    public void deletePlaylist(Long id, Authentication authentication) {
        PlaylistEntity playlistEntity = getPlaylistEntityById(id);
        if(!Objects.equals(playlistEntity.getUserEntity().getUsername(), getLoggedInUserName(authentication))){
            throw new InsufficientAuthenticationException("You do not have sufficient permissions to delete this playlist");
        }
        playlistRepository.delete(playlistEntity);
    }

    @Override
    public PlaylistDTO updatePlaylist(Long id, Playlist playlist, Authentication authentication) {
        PlaylistEntity playlistEntity = getPlaylistEntityById(id);
        if(!Objects.equals(playlistEntity.getUserEntity().getUsername(), getLoggedInUserName(authentication))){
            throw new InsufficientAuthenticationException("You do not have sufficient permissions to delete this playlist");
        }
        playlistEntity.setName(playlist.getName());
        playlistEntity.setPlaylistImageURL(playlist.getPlaylistImageURL());
        playlistRepository.saveAndFlush(playlistEntity);
        return PlaylistUtil.convertToDTO(playlistEntity);
    }

    @Override
    public List<SongDTO> getAllSongsForPlaylist(Long id) {
        PlaylistEntity playlistEntity = getPlaylistEntityById(id);
        List<SongEntity> songEntities = Optional.ofNullable(playlistEntity.getSongEntityList()).orElse(Collections.emptyList());
        return songEntities.stream()
                .map(SongUtil::convertToDTO)
                .toList();
    }

    @Override
    @Transactional
    public SongDTO addSongToPlaylist(Long playlistId, Long songId, Song song, Authentication authentication) {
        PlaylistEntity playlistEntity = getPlaylistEntityById(playlistId);
        if(!Objects.equals(playlistEntity.getUserEntity().getUsername(), getLoggedInUserName(authentication))){
            throw new InsufficientAuthenticationException("You do not have sufficient permissions to delete this playlist");
        }
        ArtistEntity artistEntity = artistRepository.findById(song.getArtist().getId()).orElseGet(() -> {
            if(song.getArtist().getId()==null){
                throw new IllegalArgumentException("Artist information does not exist, please try again with artist information");
            }
            ArtistEntity newArtistEntity = new ArtistEntity();
            newArtistEntity.setId(song.getArtist().getId());
            newArtistEntity.setName(song.getArtist().getName());
            return artistRepository.save(newArtistEntity);
        });
        SongEntity songEntity = songRepository.findById(songId).orElseGet(() -> {
            SongEntity newSongEntity = new SongEntity();
            newSongEntity.setSongId(songId);
            newSongEntity.setName(song.getName());
            newSongEntity.setArtistEntity(artistEntity);
//            newSongEntity.setPlaylist(List.of(playlistEntity));
            return songRepository.save(newSongEntity);
        });
        List<SongEntity> songEntities = Optional.ofNullable(playlistEntity.getSongEntityList()).orElse(new ArrayList<>());
        songEntities.add(songEntity);
        playlistEntity.setSongEntityList(songEntities);
        playlistRepository.save(playlistEntity);
        return SongUtil.convertToDTO(songEntity);
    }

    @Override
    @Transactional
    public void removeSongFromPlaylist(Long playlistId, Long songId, Authentication authentication) {
        PlaylistEntity playlistEntity = getPlaylistEntityById(playlistId);
        if(!Objects.equals(playlistEntity.getUserEntity().getUsername(), getLoggedInUserName(authentication))){
            throw new InsufficientAuthenticationException("You do not have sufficient permissions to delete this playlist");
        }
        List<SongEntity> songEntities = Optional.ofNullable(playlistEntity.getSongEntityList())
                .orElseThrow(() -> new EntityExistsException("The playlist is empty."));

        SongEntity songEntityToRemove = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("The song with id " + songId + " does not exist."));

        songEntities.remove(songEntityToRemove);
        playlistEntity.setSongEntityList(songEntities);
        playlistRepository.save(playlistEntity);
    }

//    ------------------------------------------------------------------------------
//    Helpers

//    TODO: Make auth check a helper method.
    private String getLoggedInUserName(Authentication authentication) {
        return ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
    }

    private PlaylistEntity getPlaylistEntityById(Long id){
        return playlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Playlist with id " + id + " not found"));
    }

    private UserEntity getUserEntityByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user with id " + username + " not found"));
    }


}
