package com.memms.melodicle.domain.dto;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {

    private Long playlistId;

    private String name;

    private String dateCreated;

    private Integer songCount;

    private String playlistImageURL;

    private String username;

    private List<SongDTO> songEntityList;


}
