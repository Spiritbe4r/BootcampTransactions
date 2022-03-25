package com.bootcamp.transactions.services;

import com.bootcamp.transactions.models.Transaction;
import java.time.LocalDateTime;
import reactor.core.publisher.Flux;


/** This is a transaction service.
 *
 *
 * @author Elvin Cardenas
 *
 *
 * @version 0.1
 */
public interface TransactionService extends CrudService<Transaction, String> {

  /**
   * Find all by identity number flux.
   *
   * @param idNumber the identity number
   * @return the flux
   */

  Flux<Transaction> findAllByIdNumber(String idNumber);

  Flux<Transaction> findFirst10ByIdNumberOrderByDateOperationDesc(String idNumber);

  Flux<Transaction> findAllByIdNumberAndDateOperationBetween(String idNumber,
       LocalDateTime fromDate, LocalDateTime toDate);
}
