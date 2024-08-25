package com.devesta.ecommerce.order.model;

import com.devesta.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount must be positive")
        BigDecimal amount,
        @NotNull(message = "payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "customer ID should be present")
        @NotBlank(message = "customer ID should be present")
        String customerId,
        @NotEmpty(message = "At least one product should be purchased")
        List<PurchaseRequest> products

) {
}
