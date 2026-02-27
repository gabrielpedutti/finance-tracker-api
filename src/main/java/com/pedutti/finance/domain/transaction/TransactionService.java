package com.pedutti.finance.domain.transaction;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

  private final TransactionRepository repository;

  public TransactionService(TransactionRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public Transaction createTransaction(TransactionRequestDTO data) {
    var transaction = new Transaction(data);
    return repository.save(transaction);
  }

  @Transactional(readOnly = true)
  public List<ListTransactionsResponseDTO> listTransactions() {
    return repository.findAll().stream()
        .map(ListTransactionsResponseDTO::new)
        .toList();
  }

  @Transactional(readOnly = true)
  public TransactionDetailDTO getTransactionById(Long id) {
    var transaction = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Transação com ID: " + id + " não encontrada."));
    return new TransactionDetailDTO(transaction);
  }

  @Transactional
  public Transaction updateTransaction(UpdateTransactionRequestDTO data) {
    var transaction = repository.findById(data.id())
        .orElseThrow(() -> new EntityNotFoundException("Transação com ID: " + data.id() + " não encontrada."));
    transaction.updateTransaction(data);
    return transaction;
  }

  @Transactional
  public void deleteTransaction(Long id) {
    repository.deleteById(id);
  }

}
