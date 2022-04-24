package com.example.sweater.repos;

import com.example.sweater.domain.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepo extends CrudRepository<Product, Long> {
}
