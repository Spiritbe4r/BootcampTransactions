package com.bootcamp.transactions.services;

import com.bootcamp.transactions.models.Transaction;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface TransactionService extends CrudService<Transaction,String> {

    Flux<Transaction> findAllByIdNumber(String idNumber);

    Flux<Transaction> findFirst10ByIdNumberOrderByDateOperationDesc(String idNumber);

    Flux<Transaction> findAllByIdNumberAndDateOperationBetween(String idNumber
            , LocalDateTime fromDate, LocalDateTime toDate);
}
