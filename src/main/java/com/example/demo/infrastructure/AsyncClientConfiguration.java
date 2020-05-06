package com.example.demo.infrastructure;

import org.asynchttpclient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AsyncClientConfiguration {

    @Bean(destroyMethod = "close")
    public AsyncHttpClient asyncHttpClient(@Autowired(required = false) AsyncHttpClientConfig asyncHttpClientConfig) {
        return new DefaultAsyncHttpClient(
                (AsyncHttpClientConfig) Optional.ofNullable(asyncHttpClientConfig).orElseGet(this::deafultHttpConfig));
    }

    private DefaultAsyncHttpClientConfig deafultHttpConfig() {
        return new DefaultAsyncHttpClientConfig.Builder()
                .setRequestTimeout(30_000)
                .build();
    }
}
