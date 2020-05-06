package com.example.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FetchServiceImpl implements FetchService {

    private final FetchServiceStandAlone fetchServiceStandAlone;

    @Override
    public String get(String url) {
        return fetchServiceStandAlone.get(url).block();
    }
}
