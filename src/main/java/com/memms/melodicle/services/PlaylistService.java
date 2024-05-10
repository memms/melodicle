package com.memms.melodicle.services;

import com.memms.melodicle.domain.dto.PlaylistDTO;
import com.memms.melodicle.domain.dto.SongDTO;
import com.memms.melodicle.domain.vo.Playlist;
import com.memms.melodicle.domain.vo.Song;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PlaylistService {

    PlaylistDTO getPlaylistMetaById(Long id);

    PlaylistDTO createPlaylist(Playlist playlist, Authentication authentication);

    void deletePlaylist(Long id, Authentication authentication);

    PlaylistDTO updatePlaylist(Long id, Playlist playlist, Authentication authentication);

    List<SongDTO> getAllSongsForPlaylist(Long id);

    SongDTO addSongToPlaylist(Long playlistId, Long songId, Song song, Authentication authentication);

    void removeSongFromPlaylist(Long playlistId, Long songId, Authentication authentication);
}
