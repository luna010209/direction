package com.example.direction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DirectionResponse {
    private Long id;
    private int busCount;
    private int subwayCount;
    private int subwayBusCount;
}
