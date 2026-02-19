package com.pedutti.finance.controller;

import com.pedutti.finance.domain.transaction.*;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public List<ListTransactionsResponseDTO> listTransactions(){
        return transactionRepository.findAll().stream().map(ListTransactionsResponseDTO::new).toList();
    }

    @PostMapping
    @Transactional
    public void registerTransaction(@RequestBody @Valid TransactionRequestDTO request){
        transactionRepository.save(new Transaction(request));
    }

    @PutMapping
    @Transactional
    public void updateTransaction(@RequestBody @Valid UpdateTransactionRequestDTO request){
        var transaction = transactionRepository.getReferenceById(request.id());
        transaction.updateTransaction(request);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteTransaction(@PathVariable Long id){
        transactionRepository.deleteById(id);
    }
}
