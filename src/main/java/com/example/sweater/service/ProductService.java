package com.example.sweater.service;

import com.example.sweater.domain.CustCharField;
import com.example.sweater.domain.Product;
import com.example.sweater.domain.ProductXUserVersion;
import com.example.sweater.domain.User;
import com.example.sweater.repos.CustCharRepo;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.repos.ProductXUserVersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CustCharRepo custCharRepo;

    @Autowired
    private ProductXUserVersionRepo productXUserVersionRepo;

    @Autowired
    private UrlService urlService;

    public Page<Product> productList(Pageable pageable, String filter, User user) {
        return productRepo.findUserProducts(pageable, user);
    }

    public void addProduct(Product product, User user) {

        urlService.setParams(product);

        Long productUrlCount = productRepo.countUrl(product.getUrl());

        if (productUrlCount == 0) {
            product.setAuthor(user);
        } else {
            Product existingProduct = productRepo.findProduct(product.getUrl());
            product.setId(existingProduct.getId());
            product.setViewers(existingProduct.getViewers());
        }

        Set<User> productViewers = product.getViewers();
        if (!productViewers.contains(user)) {
            productViewers.add(user);
        }

    }

    public void hideProduct(Product product, User user) {
        Set<User> productViewers = product.getViewers();

        for (User viewer : productViewers) {
            if (viewer.getId().equals(user.getId())) {
                productViewers.remove(viewer);
            }
        }
        productRepo.save(product);
    }

    public void addPxUVersion(Product product, User user) {
        ProductXUserVersion pxuv = new ProductXUserVersion(product, user);
        productXUserVersionRepo.save(pxuv);
    }

    @Scheduled(fixedRate = 86400000)
    public void checkUpdates() {

        for(Product product : productRepo.findAll()) {
            urlService.setParams(product);
            CustCharField productPrice = new CustCharField(product.getId(), "price", product.getPrice());
            custCharRepo.save(productPrice);
            productRepo.save(product);
        }
    }

}
