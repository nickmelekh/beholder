package com.example.sweater.service;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.dto.ProductDto;
import com.example.sweater.repos.ProductRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Service
public class UrlService {

    @Autowired
    ProductRepo productRepo;

    public void setParams(Product product) {

        Document doc = null;
        try {
            doc = Jsoup.connect(product.getUrl()).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (product.getUrl().toLowerCase().contains("ozon.ru")) {

            Elements selectResult = doc.select("script[type=application/ld+json]");

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for (Element elem : selectResult) {

                ProductDto urlProduct = gson.fromJson(elem.html(), ProductDto.class);

                product.setPrice(urlProduct.getOffers().getPrice());
                product.setUrl(urlProduct.getOffers().getUrl());
                product.setImage(urlProduct.getImage());
                product.setName(urlProduct.getName());
            }
        }
        else if (product.getUrl().toLowerCase().contains("wildberries.ru")) {
            Elements selectResult = doc.select("div[itemtype=\"http://schema.org/Product\"]");
            for (Element elem : selectResult) {
                product.setPrice(selectResult.select("meta[itemprop=\"price\"]").attr("content"));
                product.setUrl("https://www.wildberries.ru" + selectResult.select("meta[itemprop=\"url\"]").attr("content"));
                product.setImage(selectResult.select("meta[itemprop=\"image\"]").attr("content"));
                product.setName(selectResult.select("meta[itemprop=\"name\"]").attr("content"));
            }
        }

        Instant instant = Clock.system(ZoneId.of("Europe/Moscow")).instant();
        product.setValidFromDttm(instant);
    }

}
