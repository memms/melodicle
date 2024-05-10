package com.memms.melodicle.domain.dto;

import com.memms.melodicle.domain.entities.ArtistEntity;
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
public class SongDTO {

    private long songId;    //Youtube video id

    private String name;    //Song name

    private ArtistDTO artistEntity;  //Song artist

    private List<PlaylistDTO> playlist;


}
