package com.example.direction;

import com.example.direction.dto.request.DirectionRequest;
import com.example.direction.dto.response.DirectionResponse;
import com.example.direction.restTemplate.OdsayTemplate;
import com.example.direction.service.OdsayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("direction")
public class DirectionController {
    private final OdsayService service;
    private final OdsayTemplate template;

    @PostMapping("test")
    public Map<String, Object> test(@RequestBody DirectionRequest request){
        return template.directions(request);
    }
    @PostMapping
    public DirectionResponse directions(@RequestBody DirectionRequest request){
        return service.test(request);
    }
}
