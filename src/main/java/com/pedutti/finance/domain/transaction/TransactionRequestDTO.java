package com.pedutti.finance.domain.transaction;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequestDTO(
        @NotBlank
        String description,
        @Positive
        BigDecimal amount,
        LocalDate date,
        @Enumerated
        TransactionType type
) {
}
