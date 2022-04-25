package com.example.sweater.repos;

import com.example.sweater.domain.CustCharField;
import com.example.sweater.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface CustCharRepo extends CrudRepository<CustCharField, Long> {
    Iterable<CustCharField> findAll();
}
