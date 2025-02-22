package com.example.direction.api.httpInterface;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Map;

@HttpExchange(url = "https://api.odsay.com/v1/api/", accept = MediaType.APPLICATION_JSON_VALUE)
public interface OdsayInterface {
    @GetExchange("searchPubTransPathT")
    Map<String, Object> getDirections(
            @RequestParam("apiKey") String apiKey,
            @RequestParam("SX") double sx,
            @RequestParam("SY") double sy,
            @RequestParam("EX") double ex,
            @RequestParam("EY") double ey
    );
}
