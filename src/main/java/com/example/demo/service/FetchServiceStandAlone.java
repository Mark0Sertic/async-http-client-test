package com.example.demo.service;

import com.example.demo.adapter.Adapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FetchServiceStandAlone {

    private final Adapter adapter;

    public Mono<String> get(String url) {
        return adapter.get(url);
    }
}
