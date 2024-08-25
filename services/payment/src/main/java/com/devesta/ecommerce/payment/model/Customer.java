package com.devesta.ecommerce.payment.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(

        String id,
        @NotNull(message = "Customer name is required")
        String name,
        @NotNull(message = "Customer email is required")
        @Email(message = "Customer email not valid")
        String email
) {
}
