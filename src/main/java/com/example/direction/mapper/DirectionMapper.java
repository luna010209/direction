package com.example.direction.mapper;

import com.example.direction.dto.request.DirectionRequest;
import com.example.direction.dto.response.DirectionResponse;
import com.example.direction.dto.response.PathResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DirectionMapper {
    void odsayDirection(DirectionResponse direction);
    void odsayPath(PathResponse path);
    void direction(DirectionRequest request);
}
