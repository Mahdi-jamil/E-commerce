package com.devesta.ecommerce.notification;

import com.devesta.ecommerce.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerName,
        String customerEmail
) {
}
