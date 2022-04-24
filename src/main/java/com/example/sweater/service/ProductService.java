package com.example.sweater.service;

import com.example.sweater.controller.ControllerUtils;
import com.example.sweater.domain.Product;
import com.example.sweater.domain.ProductXUserVersion;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.repos.ProductXUserVersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductXUserVersionRepo productXUserVersionRepo;

    @Autowired
    private UrlService urlService;

    public Page<Product> productList(Pageable pageable, String filter, User user) {
        return productRepo.findAll(pageable, user);
    }

    public void addProduct(Product product, User user) {
        product.setAuthor(user);
        urlService.setParams(product);

        Set<User> productViewers = product.getViewers();
        if (!productViewers.contains(user)) {
            productViewers.add(user);
        }
    }

    public void addPxUVersion(Product product, User user) {
        ProductXUserVersion pxuv = new ProductXUserVersion(product, user);
        productXUserVersionRepo.save(pxuv);
    }

}
