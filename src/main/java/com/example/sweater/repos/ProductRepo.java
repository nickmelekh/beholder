package com.example.sweater.repos;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ProductRepo extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.author = :user and p.active = true")
    Page<Product> findAll(Pageable pageable, @Param("user") User user);
}
