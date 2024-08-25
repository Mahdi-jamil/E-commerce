package com.devesta.ecommerce.kafka.order;

public record Customer(
        String customerId,
        String name,
        String email
) {
}
