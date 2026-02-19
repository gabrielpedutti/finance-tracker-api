package com.pedutti.finance.domain.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "transactions")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction(TransactionRequestDTO transactionRequest) {
        this.description = transactionRequest.description();
        this.amount = transactionRequest.amount();
        this.date = transactionRequest.date();
        this.type = transactionRequest.type();
    }

    public void updateTransaction(UpdateTransactionRequestDTO request) {
        if(request.description() != null){
            this.description = request.description();
        }
        if(request.amount() != null){
            this.amount = request.amount();
        }
        if(request.date() != null){
            this.date = request.date();
        }
        if(request.type() != null){
            this.type = request.type();
        }
    }
}
