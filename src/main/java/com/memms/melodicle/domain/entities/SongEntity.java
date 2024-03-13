package com.memms.melodicle.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "songs")
public class SongEntity {

    @Id
    private long songId;    //Youtube video id

    @Column
    private String name;    //Song name

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private ArtistEntity artistEntity;  //Song artist

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "songEntityList", fetch = FetchType.LAZY) //TODO: Change cascade type later
    private List<PlaylistEntity> playlist;

}
