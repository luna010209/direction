package com.example.direction.api.webClient;

import com.example.direction.dto.request.DirectionRequest;
import com.example.direction.dto.request.OdsayRoot;
import com.example.direction.exception.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

//@RequiredArgsConstructor
@Component
public class OdsayClient {
    private final WebClient webClient;
    private final OdsayRoot root;
//
    public OdsayClient(OdsayRoot root){
        this.root = root;
        this.webClient = WebClient.builder()
                .baseUrl(root.getRoot()).build();
    }

    public Map<String, Object> directions(DirectionRequest request){
        Map map = new HashMap<>();
        try {
            String uriString = root.uriString("searchPubTransPathT");
            URI uri = UriComponentsBuilder.fromUriString(uriString)
                    .queryParam("SX", request.getSx())
                    .queryParam("SY", request.getSy())
                    .queryParam("EX", request.getEx())
                    .queryParam("EY", request.getEy())
                    .encode().build(true).toUri();

//            Mono<ResponseEntity<String>> response = webClient.post()
//                    .uri(uri)
//                    .bodyValue(request)
//                    .retrieve()
//                    .toEntity(String.class);
            String response = webClient.get()
                            .uri(uri)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
            map = new ObjectMapper().readValue(response, Map.class);
        } catch (UnsupportedEncodingException | JsonProcessingException e){
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return (Map<String, Object>) map.get("result");
//        return webClient.post()
//                .uri(uriBuilder -> uriBuilder.path(root.uriString("searchPubTransPathT")))
//                .bodyValue(request)
//                .retrieve()
//                .toEntity(DirectionResponse.class);
    }
}
