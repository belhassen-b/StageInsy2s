package com.example.tpecommerce.error.record;

public record FieldError(
        String entityName,
        String fieldName,
        String message,
        String code
) {
}
