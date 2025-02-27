package com.example.direction.service;

import com.example.direction.api.httpInterface.OdsayInterface;
import com.example.direction.dto.request.DirectionRequest;
import com.example.direction.dto.response.DirectionResponse;
import com.example.direction.dto.response.PathResponse;
import com.example.direction.exception.CustomException;
import com.example.direction.mapper.DirectionMapper;
import com.example.direction.api.restTemplate.OdsayRestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OdsayService {
    private final DirectionMapper mapper;
    private final OdsayRestTemplate template;
    private final OdsayInterface odsayInterface;

    // Insert data into DB
    public DirectionResponse test(DirectionRequest request){
        Map<String, Object> map = template.directions(request);
        DirectionResponse response = DirectionResponse.builder()
                .busCount((Integer) map.get("busCount"))
                .subwayCount((Integer) map.get("subwayCount"))
                .subwayBusCount((Integer) map.get("subwayBusCount"))
                .build();
        mapper.odsayDirection(response);
        for (Map<String, Object> path: (List<Map<String, Object>>) map.get("path")){
            Map<String, Object> info = (Map<String, Object>) path.get("info");
            PathResponse pathResponse = PathResponse.builder()
                    .pathType((Integer) path.get("pathType"))
                    .payment((Integer) info.get("payment"))
                    .totalTime((Integer) info.get("totalTime"))
//                    .mapObj((String) info.get("mapObj"))
                    .directionId(response.getId())
                    .build();
            mapper.odsayPath(pathResponse);
        }

        return response;
    }

    public DirectionResponse getDirection(Long id){
        DirectionResponse response = mapper.getDirection(id);
        if (response==null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "No exist this direction");
        return response;
    }

    public DirectionResponse updateDirection(Long id, DirectionRequest request){
        Map<String, Object> map = template.directions(request);
        DirectionResponse direction = mapper.getDirection(id);
        if (direction==null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "No exist this direction");

        direction.setBusCount((Integer) map.get("busCount"));
        direction.setSubwayCount((Integer) map.get("subwayCount"));
        direction.setSubwayBusCount((Integer) map.get("subwayBusCount"));

        mapper.updateDirection(direction);

        return direction;
    }

    public boolean deleteDirection(Long id){
        DirectionResponse direction = mapper.getDirection(id);
        if (direction==null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "No exist this direction");
        mapper.deleteDirection(id);
        return true;
    }

    public List<PathResponse> getAllPath(Long directionId){
        DirectionResponse direction = mapper.getDirection(directionId);
        if (direction==null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "No exist direction");
        return mapper.getAllPath(directionId);
    }

    public List<PathResponse> getCheapPath(Long directionId, Integer paymentLimit){
        DirectionResponse direction = mapper.getDirection(directionId);
        if (direction==null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "No exist direction");
        return mapper.getCheapPath(directionId, paymentLimit);
    }

}
