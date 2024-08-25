package com.devesta.ecommerce.product.web;


import com.devesta.ecommerce.product.model.ProductPurchaseRequest;
import com.devesta.ecommerce.product.model.ProductPurchaseResponse;
import com.devesta.ecommerce.product.model.ProductRequest;
import com.devesta.ecommerce.product.model.ProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        return new ResponseEntity<>(service.createProduct(request), HttpStatus.CREATED);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        return new ResponseEntity<>(service.purchaseProducts(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Integer id
    ) {
        return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }


}
