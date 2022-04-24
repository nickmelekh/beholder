package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class ProductXUserVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long user_id;
    private Long product_id;
    private Instant validFromDttm;
    private boolean active;

    public ProductXUserVersion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Instant getValidFromDttm() {
        return validFromDttm;
    }

    public void setValidFromDttm(Instant validFromDttm) {
        this.validFromDttm = validFromDttm;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ProductXUserVersion(Product product, User user) {
        this.user_id = user.getId();
        this.product_id = product.getId();
        this.active = true;
    }
}
