package com.memms.melodicle.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "playlists")
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    @Column
    private String name;

    @Column
    private String dateCreated;

    @Column
    private Integer songCount;

    @ManyToOne(cascade = CascadeType.ALL)   //TODO: Change appropriately
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)  //TODO: Change appropriately
    @JoinTable(
            name = "playlist_songs",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<SongEntity> songEntityList;
}
