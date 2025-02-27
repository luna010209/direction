package com.example.direction.mapper;

import com.example.direction.dto.request.DirectionRequest;
import com.example.direction.dto.response.DirectionResponse;
import com.example.direction.dto.response.PathResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DirectionMapper {
    void odsayDirection(DirectionResponse direction);
    void odsayPath(PathResponse path);

    DirectionResponse getDirection(Long id);
    List<PathResponse> getAllPath(Long directionId);
    List<PathResponse> getCheapPath(Long directionId, Integer paymentLimit);
    void updateDirection(DirectionResponse direction);
    void deleteDirection(Long id);

}
