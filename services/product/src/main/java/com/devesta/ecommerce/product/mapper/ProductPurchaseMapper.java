package com.devesta.ecommerce.product.mapper;

import com.devesta.ecommerce.product.model.Product;
import com.devesta.ecommerce.product.model.ProductPurchaseResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductPurchaseMapper {

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                quantity,
                product.getPrice()
        );
    }

}
