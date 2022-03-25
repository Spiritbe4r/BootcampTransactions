package com.bootcamp.transactions.services.Impl;

import com.bootcamp.transactions.models.Transaction;
import com.bootcamp.transactions.repository.TransactionRepo;
import com.bootcamp.transactions.services.TransactionService;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepo transactionRepo;

  @Override
  public Flux<Transaction> getAll() {
    return transactionRepo.findAll();
  }

  @Override
  public Mono<Transaction> getById(String s) {
    return transactionRepo.findById(s);
  }

  @Override
  public Mono<Transaction> save(Transaction obj) {
    return transactionRepo.insert(obj);
  }

  @Override
  public Mono<Transaction> update(Mono<Transaction> obj, String s) {
    return transactionRepo.findById(s)
                .doOnNext(e -> e.setId(s))
                .flatMap(transactionRepo::save);
  }

  @Override
  public Mono<Void> delete(String s) {
    return transactionRepo.deleteById(s);
  }

  @Override
  public Flux<Transaction> findAllByIdNumber(String idNumber) {
    return transactionRepo.findAllByIdNumber(idNumber);
  }

  @Override
  public Flux<Transaction> findFirst10ByIdNumberOrderByDateOperationDesc(String idNumber) {
    return transactionRepo.findFirst10ByIdNumberOrderByDateOperationDesc(idNumber);
  }

  @Override
  public Flux<Transaction> findAllByIdNumberAndDateOperationBetween(
        String idNumber, LocalDateTime fromDate, LocalDateTime toDate) {
    return transactionRepo.findAllByIdNumberAndDateOperationBetween(idNumber, fromDate, toDate);
  }
}
