package com.example.direction.dto.request;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
@Getter
public class OdsayRoot {
    @Value("${odsay.apiKey}")
    private String apiKey;
    private String root = "https://api.odsay.com/v1/api/";

    public String uriString(String endpoint) throws UnsupportedEncodingException {
        String url = UriComponentsBuilder.fromUriString(root).path(endpoint)
                .queryParam("apiKey", URLEncoder.encode(apiKey, "UTF-8"))
                .encode().build(true).toUriString();
        return url;
    }
}
