package com.example.demo.controller;

import com.example.demo.adapter.Adapter;
import com.example.demo.service.FetchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Value("${url}")
    private String testUrl;

    private final Adapter adapter;
    private final FetchService fetchService;

    public TestController(Adapter adapter, FetchService fetchService) {
        this.adapter = adapter;
        this.fetchService = fetchService;
    }

    @PostMapping
    public ResponseEntity<Mono<String>> execute(@RequestBody String body) {
        var response = adapter.create(testUrl, body)
                              //TODO uncomment bellow statement to work
                              //.publishOn(Schedulers.elastic())
                              .map(resp -> fetchService.get(testUrl));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
