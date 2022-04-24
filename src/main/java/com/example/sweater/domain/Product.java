package com.example.sweater.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;
//    public Instant validFromDttm;
    @Transient
    private Offer offers;
    private String price;
    @NotBlank(message = "Please fill the message")
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany
    @JoinTable(
        name = "product_x_user",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> viewers = new HashSet<>();

//    public Instant getValidFromDttm() {
//        return validFromDttm;
//    }
//
//    public void setValidFromDttm(Instant validFromDttm) {
//        this.validFromDttm = validFromDttm;
//    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Offer getOffers() {
        return offers;
    }

    public void setOffers(Offer offers) {
        this.offers = offers;
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

    public Product() {
    }

    public Set<User> getViewers() {
        return viewers;
    }

    public void setViewers(Set<User> viewers) {
        this.viewers = viewers;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", offers=" + offers +
                ", price='" + price + '\'' +
                ", url='" + url + '\'' +
                ", author=" + author +
                ", viewers=" + viewers +
                '}';
    }

    public void printViewers() {
        Iterator itr = this.viewers.iterator();
        while(itr.hasNext()){
            System.out.print(itr.next() + ",");
        }
    }
}
