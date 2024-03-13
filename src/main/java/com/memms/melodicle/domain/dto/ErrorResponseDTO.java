package com.memms.melodicle.domain.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDTO {

    private int errorCode;
    private String message;

}
