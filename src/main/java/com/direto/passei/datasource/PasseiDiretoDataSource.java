package com.direto.passei.datasource;


import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class PasseiDiretoDataSource {

    private final WebClient webClient;

    public PasseiDiretoDataSource(WebClient webClient) {
        this.webClient = webClient;

    }

    public Elements getResults() throws IOException {

        var results = Jsoup.connect("https://www.passeidireto.com/arquivo/51280157/relatorio-de-estagio-em-alimentos").get();
//                webClient.get()
//                .uri("https://www.passeidireto.com/arquivo/51280157/relatorio-de-estagio-em-alimentos")
//                .header("content-type", MediaType.APPLICATION_JSON_VALUE)
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError,  response ->
//                        response.bodyToMono(Object.class)
//                                .flatMap(body ->
//                                         a)
//                )
//                .onStatus(HttpStatus::is5xxServerError, response -> {
//                    throw new InternalServerErrorException("SERVICE--CONTA-DEBITO-AUTOMATICO");
//                })
//                .bodyToMono(HtmlResponse.class)
//                .block();

        var pre = results.getElementsByTag("pre");

        return pre;
    }
}
