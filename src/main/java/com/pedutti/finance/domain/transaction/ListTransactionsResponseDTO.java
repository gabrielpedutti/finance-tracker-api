package com.pedutti.finance.domain.transaction;

public record ListTransactionsResponseDTO(
        Long id,
        String description,
        String amount,
        String date,
        String type
) {
    public ListTransactionsResponseDTO(Transaction transaction) {
        this(
            transaction.getId(),
            transaction.getDescription(),
            transaction.getAmount().toString(),
            transaction.getDate().toString(),
            transaction.getType().name()
        );
    }
}
