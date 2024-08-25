package com.devesta.ecommerce.kafka;

import com.devesta.ecommerce.customer.CustomerResponse;
import com.devesta.ecommerce.order.model.PaymentMethod;
import com.devesta.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
