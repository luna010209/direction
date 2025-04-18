package com.example.direction.api.restTemplate;

import com.example.direction.dto.request.DirectionRequest;
import com.example.direction.api.OdsayRoot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OdsayRestTemplate {
    private final OdsayRoot root;
    public Map<String, Object> directions(DirectionRequest request){
        Map map = new HashMap<>();
        try {
            String uriString = root.uriString("searchPubTransPathT");
            URI uri = UriComponentsBuilder.fromUriString(uriString)
                    .queryParam("lang", request.getLang())
                    .queryParam("SX", request.getSx())
                    .queryParam("SY", request.getSy())
                    .queryParam("EX", request.getEx())
                    .queryParam("EY", request.getEy())
                    .encode().build(true).toUri();

            RestTemplate restTemplate = new RestTemplate();

            String response = restTemplate.getForObject(uri, String .class);
            map = new ObjectMapper().readValue(response, Map.class);
        } catch (UnsupportedEncodingException | JsonProcessingException e){
            e.getMessage();
        }
        return (Map<String, Object>) map.get("result");
    }
}
