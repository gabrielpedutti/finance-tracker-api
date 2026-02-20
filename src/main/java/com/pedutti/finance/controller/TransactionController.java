package com.pedutti.finance.controller;

import com.pedutti.finance.domain.transaction.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public ResponseEntity<List<ListTransactionsResponseDTO>> listTransactions(){
        var allTransactions = transactionRepository.findAll().stream().map(ListTransactionsResponseDTO::new).toList();

        return ResponseEntity.ok(allTransactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetailDTO> getTransactionById(@PathVariable Long id) {
        var transaction = transactionRepository.getReferenceById(id);

        return ResponseEntity.ok(new TransactionDetailDTO(transaction));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TransactionDetailDTO> registerTransaction(@RequestBody @Valid TransactionRequestDTO request, UriComponentsBuilder uriBuilder){
        var transaction = new Transaction(request);
        transactionRepository.save(transaction);

        var uri = uriBuilder.path("/transactions/{id}").buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(uri).body(new TransactionDetailDTO(transaction));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TransactionDetailDTO> updateTransaction(@RequestBody @Valid UpdateTransactionRequestDTO request){
        var transaction = transactionRepository.getReferenceById(request.id());
        transaction.updateTransaction(request);

        return ResponseEntity.ok(new TransactionDetailDTO(transaction));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        transactionRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
