package com.memms.melodicle.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO {

    private Long id;

    private String name;

    private List<SongDTO> songDTOS;

}
