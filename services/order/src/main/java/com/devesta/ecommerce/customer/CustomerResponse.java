package com.devesta.ecommerce.customer;

public record CustomerResponse(
        String customerId,
        String name,
        String email
) {
}
