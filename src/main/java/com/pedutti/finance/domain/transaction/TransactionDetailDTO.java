package com.pedutti.finance.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDetailDTO(
    Long id,
    String description,
    BigDecimal amount,
    TransactionType type,
    LocalDate date
) {
    public TransactionDetailDTO(Transaction transaction) {
        this(
            transaction.getId(),
            transaction.getDescription(),
            transaction.getAmount(),
            transaction.getType(),
            transaction.getDate()
        );
    }
}
