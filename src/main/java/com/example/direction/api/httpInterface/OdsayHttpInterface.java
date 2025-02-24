package com.example.direction.api.httpInterface;

import com.example.direction.dto.request.DirectionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OdsayHttpInterface {
    @Value("${odsay.apiKey}")
    private String apiKey;
    private final OdsayInterface odsayInterface;

    public Map<String, Object> directions(DirectionRequest request) {
        Map<String, Object> map = odsayInterface.getDirections(apiKey,
                request.getSx(), request.getSy(),
                request.getEx(), request.getEy());
        return (Map<String, Object>) map.get("result");
    }
}
