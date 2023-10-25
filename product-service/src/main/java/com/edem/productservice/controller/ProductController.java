package com.edem.productservice.controller;

import com.edem.productservice.dto.ProductRequest;
import com.edem.productservice.dto.ProductResponse;
import com.edem.productservice.model.Product;
import com.edem.productservice.repository.ProductRepository;
import com.edem.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createResponse (@RequestBody ProductRequest productRequest){
        service.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return service.getAllProducts();
    }

}
