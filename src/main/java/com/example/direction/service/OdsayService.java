package com.example.direction.service;

import com.example.direction.dto.request.DirectionRequest;
import com.example.direction.dto.response.DirectionResponse;
import com.example.direction.dto.response.PathResponse;
import com.example.direction.mapper.DirectionMapper;
import com.example.direction.restTemplate.OdsayTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OdsayService {
    private final DirectionMapper mapper;
    private final OdsayTemplate template;
    public DirectionResponse test(DirectionRequest request){
        Map<String, Object> map = template.directions(request);
        for (Map<String, Object> path: (List<Map<String, Object>>) map.get("path")){
            Map<String, Object> info = (Map<String, Object>) path.get("info");
            PathResponse pathResponse = PathResponse.builder()
                    .pathType((Integer) path.get("pathType"))
                    .payment((Integer) info.get("payment"))
                    .totalTime((Integer) info.get("totalTime"))
                    .mapObj((String) info.get("mapObj"))
                    .build();
            mapper.odsayPath(pathResponse);
        }
        DirectionResponse response = DirectionResponse.builder()
                .busCount((Integer) map.get("busCount"))
                .subwayCount((Integer) map.get("subwayCount"))
                .subwayBusCount((Integer) map.get("subwayBusCount"))
                .build();
        return response;
    }

}
