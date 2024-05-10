package com.memms.melodicle.domain.vo;

import com.memms.melodicle.domain.entities.ArtistEntity;
import com.memms.melodicle.domain.entities.PlaylistEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Song {

    private long songId;    //Youtube video id

    private String name;    //Song name

    private Artist artist;

    private List<Playlist> playlist;
}
