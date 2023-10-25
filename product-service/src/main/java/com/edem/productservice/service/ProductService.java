package com.edem.productservice.service;

import com.edem.productservice.dto.ProductRequest;
import com.edem.productservice.dto.ProductResponse;
import com.edem.productservice.model.Product;
import com.edem.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public void createProduct( ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        repository.save(product);
        log.info("Product {} saved", product.getId());
}

    public List<ProductResponse>  getAllProducts() {
        List<Product> products =  repository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
    }
