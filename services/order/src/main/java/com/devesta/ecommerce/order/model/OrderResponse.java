package com.devesta.ecommerce.order.model;

import java.math.BigDecimal;

public record OrderResponse(
    Integer orderId,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId
) {
}
