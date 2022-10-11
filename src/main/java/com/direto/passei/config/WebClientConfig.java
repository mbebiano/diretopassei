package com.direto.passei.config;


import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {


    public WebClientConfig() {

    }

    @Bean
    public WebClient webClient() {

        var provider =
                ConnectionProvider.builder("custom")
                        .maxIdleTime(Duration.ofSeconds(25))
                        .maxLifeTime(Duration.ofSeconds(30))
                        .pendingAcquireMaxCount(-1)
                        .build();

        var httpClient = HttpClient.create(provider)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000);

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

}