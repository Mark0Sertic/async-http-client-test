package com.example.demo.infrastructure;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.AllArgsConstructor;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;

@AllArgsConstructor
@Configuration
public class HttpConfiguration {

    private final HttpProperties httpProperties;

    @Bean
    public AsyncHttpClientConfig asyncHttpClientConfig() throws SSLException {

        return Dsl.config()
                  .setSslContext(SslContextBuilder.forClient()
                                                  .trustManager(InsecureTrustManagerFactory.INSTANCE)
                                                  .build())
                  .setRequestTimeout(httpProperties.getRequestTimeout())
                  .setReadTimeout(4000)
                  .setMaxConnectionsPerHost(500)
                  .setMaxConnections(500)
                  //TODO if setKeepAlive is set to false setup code also works
                  .build();
    }

}
