package com.pedutti.finance.controller;

import com.pedutti.finance.domain.transaction.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<ListTransactionsResponseDTO>> listTransactions(){
        var allTransactions = transactionService.listTransactions();

        return ResponseEntity.ok(allTransactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetailDTO> getTransactionById(@PathVariable Long id) {
        var transaction = transactionService.getTransactionById(id);

        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionDetailDTO> registerTransaction(@RequestBody @Valid TransactionRequestDTO request, UriComponentsBuilder uriBuilder){
        var transaction = transactionService.createTransaction(request);
        var uri = uriBuilder.path("/transactions/{id}").buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(uri).body(new TransactionDetailDTO(transaction));
    }

    @PutMapping
    public ResponseEntity<TransactionDetailDTO> updateTransaction(@RequestBody @Valid UpdateTransactionRequestDTO request){
        var transaction = transactionService.updateTransaction(request);

        return ResponseEntity.ok(new TransactionDetailDTO(transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);

        return ResponseEntity.noContent().build();
    }
}
