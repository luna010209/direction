package com.example.direction;

import com.example.direction.api.httpInterface.OdsayHttpInterface;
import com.example.direction.dto.request.DirectionRequest;
import com.example.direction.dto.response.DirectionResponse;
import com.example.direction.api.restTemplate.OdsayRestTemplate;
import com.example.direction.dto.response.PathResponse;
import com.example.direction.service.OdsayService;
import com.example.direction.api.webClient.OdsayWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("direction")
public class DirectionController {
    private final OdsayService service;
    private final OdsayRestTemplate template;
    private final OdsayWebClient client;
    private final OdsayHttpInterface httpInterface;


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
        return httpInterface.directions(request);
    }

    @PostMapping("my-batis")
    public DirectionResponse directions(@RequestBody DirectionRequest request){
        return service.test(request);
    }

    @GetMapping("my-batis/{direction-id}")
    public DirectionResponse getDirection(@PathVariable("direction-id") Long id){
        return service.getDirection(id);
    }

    @PutMapping("my-batis/{direction-id}")
    public DirectionResponse updateDirection(
            @PathVariable("direction-id") Long id,
            @RequestBody DirectionRequest request
    ){
        return service.updateDirection(id, request);
    }

    @DeleteMapping("my-batis/{direction-id}")
    public boolean deleteDirection(
            @PathVariable("direction-id") Long id
    ){
        return service.deleteDirection(id);
    }

    @GetMapping("my-batis/all-path/{directionId}")
    public List<PathResponse> getAllPath(@PathVariable("directionId") Long directionId){
        return service.getAllPath(directionId);
    }

    @GetMapping("my-batis/cheap-path/{directionId}/{paymentLimit}")
    public List<PathResponse> getCheapPath(
            @PathVariable("directionId") Long directionId,
            @PathVariable("paymentLimit") Integer paymentLimit
    ){
        return service.getCheapPath(directionId, paymentLimit);
    }
}
