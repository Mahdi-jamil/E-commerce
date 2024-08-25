package com.devesta.ecommerce.payment;


import com.devesta.ecommerce.customer.CustomerResponse;
import com.devesta.ecommerce.order.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
