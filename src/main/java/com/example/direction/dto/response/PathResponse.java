package com.example.direction.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class PathResponse {
    private Long pathId;
    private int pathType;
    private int payment;
    private int totalTime;
    @Getter
    private String mapObj;
}
