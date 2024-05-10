package com.memms.melodicle.utility;

import com.memms.melodicle.domain.dto.ArtistDTO;
import com.memms.melodicle.domain.dto.SongDTO;
import com.memms.melodicle.domain.entities.ArtistEntity;
import com.memms.melodicle.domain.entities.SongEntity;

public class SongUtil {

    public static SongDTO convertToDTO(SongEntity songEntity) {
        return SongDTO.builder()
                .songId(songEntity.getSongId())
                .name(songEntity.getName())
                .artistDTO(artistToDTO(songEntity.getArtistEntity()))
                .build();
    }

    private static ArtistDTO artistToDTO(ArtistEntity artistEntity) {
        return ArtistDTO.builder()
                .id(artistEntity.getId())
                .name(artistEntity.getName())
                .build();
    }
}
