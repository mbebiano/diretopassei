package com.direto.passei.transportlayer;

import com.direto.passei.entity.HtmlResponse;
import com.direto.passei.usecase.BuscarHtml;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiretoPassei {

    @Autowired
    private BuscarHtml buscarHtml;

    @GetMapping(
            value = "/passei-direto/consulta",
            produces = {"application/json"}
    )
    public ResponseEntity<String> getHtmlPage(

    ) {
        HtmlResponse htmlResponse = new HtmlResponse();
        Elements elements = buscarHtml.getPagina();
        Element element = elements.get(0);
        return ResponseEntity.status(HttpStatus.OK).body(element.outerHtml());
    }

}
