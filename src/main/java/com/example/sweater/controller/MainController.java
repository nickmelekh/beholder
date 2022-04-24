package com.example.sweater.controller;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UrlService urlService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Product> products = productRepo.findAll();

        model.addAttribute("products", products);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Product product,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        product.setAuthor(user);

        urlService.setParams(product);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", null);
            product.setActive(true);
            productRepo.save(product);
        }

        Iterable<Product> products = productRepo.findAll();

        model.addAttribute("products", products);

        return "main";
    }
}
