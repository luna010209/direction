package com.example.direction.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectionRequest {
    private int lang;
    private double sx;
    private double sy;
    private double ex;
    private double ey;
}
