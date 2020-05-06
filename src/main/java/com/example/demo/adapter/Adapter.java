package com.example.demo.adapter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.*;
import org.asynchttpclient.util.HttpConstants;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class Adapter {

    private final AsyncHttpClient asyncHttpClient;

    public Mono<String> create(String url, String body) {
        var request = new RequestBuilder(HttpConstants.Methods.POST)
                .setUrl(url)
                .addHeader("Content-Type", "application/json")
                .setBody(body)
                .build();
        return Mono.fromFuture(asyncHttpClient.executeRequest(request).toCompletableFuture())
                   .map(response -> response.getResponseBody());
    }

    public Mono<String> get(String url) {
        var request = new RequestBuilder(HttpConstants.Methods.GET)
                .setUrl(url)
                .build();
        return Mono.fromFuture(asyncHttpClient.executeRequest(request).toCompletableFuture())
                   .map(response -> response.getResponseBody());
    }
}
