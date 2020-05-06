package com.example.demo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean(destroyMethod = "stop")
    public WireMockServer wireMockServer(@Value("${wiremock.port}") Integer wiremockPort) {
        WireMock.configureFor(wiremockPort);

        var wireMockServer = new WireMockServer(wiremockPort);
        wireMockServer.start();
        return wireMockServer;
    }
}
