package com.memms.melodicle.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artists")
public class ArtistEntity {

    @Id
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistEntity")    //TODO: Change Accordingly
    private List<SongEntity> songEntityList;
}
