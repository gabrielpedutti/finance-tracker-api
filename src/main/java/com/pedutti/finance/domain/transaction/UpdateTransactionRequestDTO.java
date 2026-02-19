package com.pedutti.finance.domain.transaction;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateTransactionRequestDTO(
        @NotNull
        Long id,
        String description,
        BigDecimal amount,
        LocalDate date,
        TransactionType type
) {
}
