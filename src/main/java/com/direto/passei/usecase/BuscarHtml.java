package com.direto.passei.usecase;

import com.direto.passei.datasource.PasseiDiretoDataSource;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BuscarHtml {

    private final PasseiDiretoDataSource passeiDiretoDataSource;

    public BuscarHtml(PasseiDiretoDataSource passeiDiretoDataSource) {
        this.passeiDiretoDataSource = passeiDiretoDataSource;
    }

    public Elements getPagina()  {
        Elements results = null;
        try {
            results = passeiDiretoDataSource.getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}
