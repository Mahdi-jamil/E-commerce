package com.devesta.ecommerce.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer name is required")
        String name,
        @NotNull(message = "Customer email is required")
        @Email(message = "Email not valid")
        String email,
        Address address
) {
}
