package com.memms.melodicle.controller;

import com.memms.melodicle.domain.dto.PlaylistDTO;
import com.memms.melodicle.domain.dto.SongDTO;
import com.memms.melodicle.domain.vo.Playlist;
import com.memms.melodicle.domain.vo.Song;
import com.memms.melodicle.services.PlaylistService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlist")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;


//    Get user Playlist list

//    Get Playlist info

    @Operation(summary = "Get a playlist info by id")
    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable("id") Long id){
        PlaylistDTO playlistDTO = playlistService.getPlaylistMetaById(id);
        return new ResponseEntity<>(playlistDTO, HttpStatus.OK);
    }

//    Make a new playlist
    @Operation(summary = "Create new playlist for currently logged in user")
    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@Validated @RequestBody Playlist playlist, Authentication authentication) {
        PlaylistDTO playlistDTO = playlistService.createPlaylist(playlist, authentication);
        return new ResponseEntity<>(playlistDTO, HttpStatus.CREATED);
    }


//    Remove playlist

    @Operation(summary = "Delete a playlist for currently logged in user")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable("id") Long id, Authentication authentication) {
        playlistService.deletePlaylist(id, authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Update (Put) a playlist
    @Operation(summary = "Update a playlist if owned by logged in user")
    @PutMapping("/{id}")
    public ResponseEntity<PlaylistDTO> updatePlaylistById(@PathVariable("id") Long id, @Validated @RequestBody Playlist playlist, Authentication authentication){
        return new ResponseEntity<>(playlistService.updatePlaylist(id, playlist, authentication), HttpStatus.OK);
    }

//    Get the songs inside a playlist
    @Operation(summary = "Get song list in a playlist")
    @GetMapping("/{id}/songs")
    public ResponseEntity<?> getSongsInPlaylistById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(playlistService.getAllSongsForPlaylist(id), HttpStatus.OK);
    }


//    Add song to playlist
    @Operation(summary = "Add a song to playlist")
    @PostMapping("/{id}/song/{sid}")
    public ResponseEntity<SongDTO> addSongToPlaylist(@PathVariable("id") Long playlistId, @PathVariable("sid") Long songId,
                                                     @Validated @RequestBody Song song, Authentication authentication) {
        return new ResponseEntity<>(playlistService.addSongToPlaylist(playlistId, songId, song, authentication), HttpStatus.CREATED);
    }

//    Remove song from playlist
    @Operation(summary = "Remove a song from playlist")
    @DeleteMapping("/{id}/song/{sid}")
    public ResponseEntity<?> removeSongFromPlaylist(@PathVariable("id") Long playlistId, @PathVariable("sid") Long songId, Authentication authentication) {
        playlistService.removeSongFromPlaylist(playlistId, songId, authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//

}
