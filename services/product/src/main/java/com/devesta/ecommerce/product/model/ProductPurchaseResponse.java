package com.devesta.ecommerce.product.model;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
         Integer productId,
         String name,
         String description,
         double quantity,
         BigDecimal price
) {
}
