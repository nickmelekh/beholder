package com.example.sweater.controller;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.repos.ProductXUserVersionRepo;
import com.example.sweater.repos.UserRepo;
import com.example.sweater.service.ProductService;
import com.example.sweater.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            @AuthenticationPrincipal User user,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {

        model.addAttribute("url", "/main");
        Page<Product> page = this.productService.productList(pageable, "", user);
        model.addAttribute("page", page);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Product product,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {
        productService.addProduct(product, user);

        if (bindingResult.hasErrors() || StringUtils.isEmpty(product.getUrl())) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            if (StringUtils.isEmpty(product.getUrl())) {
                errorsMap.put("urlError", "Ссылка некорректна");
            }
            System.out.println(errorsMap.toString());

            model.mergeAttributes(errorsMap);
            model.addAttribute("product", product);

        } else {
            model.addAttribute("product", null);
            productRepo.save(product);
            productService.addPxUVersion(product, user);
        }

        model.addAttribute("url", "/main");
        Page<Product> page = this.productService.productList(pageable, "", user);
        model.addAttribute("page", page);

        return "main";
    }

    @GetMapping("/main/{product}/delete")
    public String deleteProduct(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Product product,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer
    ) throws IOException {

        productService.hideProduct(product, currentUser);

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        return "redirect:" + components.getPath();
    }
}
