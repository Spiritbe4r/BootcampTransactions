package com.bootcamp.transactions.repository;

import com.bootcamp.transactions.models.Transaction;
import java.time.LocalDateTime;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * This is a implementacion of reactive mongo repository.
 *
 */
public interface TransactionRepo extends ReactiveMongoRepository<Transaction, String> {

  Flux<Transaction> findAllByIdNumber(String idNumber);

  Flux<Transaction> findFirst10ByIdNumberOrderByDateOperationDesc(String idNumber);

  Flux<Transaction> findAllByIdNumberAndDateOperationBetween(String idNumber,
      LocalDateTime fromDate, LocalDateTime toDate);
}
