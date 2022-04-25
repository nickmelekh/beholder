package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Entity
public class CustCharField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Instant validFromDttm;
    private String fieldName;
    private String fieldValue;

    public CustCharField(Long productId, String fieldName, String fieldValue) {
        this.productId = productId;
        Instant instant = Clock.system(ZoneId.of("Europe/Moscow")).instant();
        this.validFromDttm = instant;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public CustCharField() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Instant getValidFromDttm() {
        return validFromDttm;
    }

    public void setValidFromDttm(Instant validFromDttm) {
        this.validFromDttm = validFromDttm;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
