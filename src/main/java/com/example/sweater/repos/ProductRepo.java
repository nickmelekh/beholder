package com.example.sweater.repos;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ProductRepo extends CrudRepository<Product, Long> {
    @Query("select p from Product p inner join p.viewers v where v = :user")
    Page<Product> findUserProducts(Pageable pageable, @Param("user") User user);

    @Query("select count(p) from Product p where p.url = :url")
    Long countUrl(@Param("url") String url);

    @Query("select p from Product p where p.url = :url")
    Product findProduct(@Param("url") String url);

    @Query("select p from Product p")
    Iterable<Product> findAll();
}
