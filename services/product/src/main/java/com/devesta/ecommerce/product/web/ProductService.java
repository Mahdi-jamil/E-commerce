package com.devesta.ecommerce.product.web;

import com.devesta.ecommerce.exceptions.ProductNotFoundException;
import com.devesta.ecommerce.exceptions.ProductPurchaseException;
import com.devesta.ecommerce.product.mapper.ProductMapper;
import com.devesta.ecommerce.product.mapper.ProductPurchaseMapper;
import com.devesta.ecommerce.product.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductPurchaseMapper purchaseMapper;

    public Integer createProduct(ProductRequest request) {
        Product saved = repository.save(mapper.toProduct(request));
        return saved.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<ProductPurchaseResponse> purchaseResponse = new ArrayList<>();

        for (ProductPurchaseRequest purchaseRequest : request) {
            Product product = repository.findById(purchaseRequest.productId())
                    .orElseThrow(() -> new ProductPurchaseException("Some products you order does not exists"));
            if (product.getAvailableQuantity() < purchaseRequest.quantity())
                throw new ProductPurchaseException(
                        String.format("Insufficient stock quantity for products with id: %d", purchaseRequest.productId())
                );
            var newAvailableQuantity = product.getAvailableQuantity() - purchaseRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchaseResponse.add(purchaseMapper.toProductPurchaseResponse(product, purchaseRequest.quantity()));
        }
        return purchaseResponse;
    }

    public ProductResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("product with id: %d not found", id)
                ));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
