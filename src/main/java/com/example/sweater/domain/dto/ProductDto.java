package com.example.sweater.domain.dto;

import com.example.sweater.domain.Offer;

public class ProductDto {
    private Long id;
    private String name;
    private String image;
    private Offer offers;
    private String price;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Offer getOffers() {
        return offers;
    }

    public void setOffers(Offer offers) {
        this.offers = offers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
