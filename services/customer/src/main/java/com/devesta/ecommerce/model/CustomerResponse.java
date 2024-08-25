package com.devesta.ecommerce.model;

public record CustomerResponse(
        String customerId,
        String name,
        String email,
        Address address
) {
}
