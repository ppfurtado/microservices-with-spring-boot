package com.mangatech.productservice.service;

import com.mangatech.productservice.dto.ProductRequest;
import com.mangatech.productservice.dto.ProductResponse;
import com.mangatech.productservice.model.Product;
import com.mangatech.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    public void createProduct(ProductRequest request){
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        repository.save(product);
        log.info("Product is saved");
    }

    public List<ProductResponse> getAllProducts(){
       List<Product> products = repository.findAll();
       return products.stream().map(this::ProductToProductResponse).toList();
    }

    private ProductResponse ProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
