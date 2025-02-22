package com.example.direction;

import com.example.direction.dto.request.DirectionRequest;
import com.example.direction.dto.response.DirectionResponse;
import com.example.direction.api.restTemplate.OdsayTemplate;
import com.example.direction.service.OdsayService;
import com.example.direction.api.webClient.OdsayClient;
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
    private final OdsayClient client;

    @PostMapping("my-batis")
    public DirectionResponse directions(@RequestBody DirectionRequest request){
        return service.test(request);
    }

    @PostMapping("rest-template")
    public Map<String, Object> restTemplate(@RequestBody DirectionRequest request){
        return template.directions(request);
    }

    @PostMapping("web-client")
    public Map<String, Object> webClient(@RequestBody DirectionRequest request){
        return client.directions(request);
    }

    @PostMapping("http-interface")
    public Map<String, Object> odsayInterface(@RequestBody DirectionRequest request){
        return service.odsayInterface(request);
    }
}
