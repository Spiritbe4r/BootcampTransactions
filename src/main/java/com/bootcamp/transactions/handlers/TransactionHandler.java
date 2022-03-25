package com.bootcamp.transactions.handlers;

import com.bootcamp.transactions.models.Transaction;
import com.bootcamp.transactions.services.TransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/*** This class is a functional Handler
 * to manage the transactions.
 *
 * @author Elvin Cardenas
 *
 *
 * @version 0.1
 */
@AllArgsConstructor
@Component
public class TransactionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionHandler.class);

  private final TransactionService transactionService;

  /**
   * Find transactions by identity number mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> findTransactionsByIdNumber(ServerRequest request) {
    String identityNumber = request.pathVariable("idNumber");

    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transactionService.findAllByIdNumber(identityNumber), Transaction.class);
  }

  /**
   * Find transactions all transactions.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> findAll(final ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transactionService.getAll(), Transaction.class);
  }

  /**
   * Find all comissions number mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> findAllCommissions(ServerRequest request) {
    String identityNumber = request.pathVariable("idNumber");
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
       .body(transactionService.getAll()
       .filter(transaction -> transaction.getTypeTransaction().equals("COMMISSION")
       && transaction.getIdNumber().equals(identityNumber)), Transaction.class);
  }


  /**
   * Search the last 10 transactions in a range of dates ".
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> reportLastTen(ServerRequest request) {
    String identityNumber = request.pathVariable("idNumber");
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(transactionService.findFirst10ByIdNumberOrderByDateOperationDesc(
              identityNumber), Transaction.class);
  }

  /**
   * create a New transaction mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> newTransaction(ServerRequest request) {
    Mono<Transaction> transactionMono = request.bodyToMono(Transaction.class);
    return transactionMono.flatMap(transactionService::save)
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)));
  }

  /**
   * Search transactions in a range of dates with the format "yyyy-MM-dd HH:mm:ss".
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> findTransactionsByRangeOfDates(ServerRequest request) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    String identityNumber = request.pathVariable("idNumber");

    LocalDateTime fromDate = LocalDateTime.parse(request.pathVariable("fromDate"), formatter);
    LocalDateTime toDate = LocalDateTime.parse(request.pathVariable("toDate"), formatter);
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
              .body(transactionService.findAllByIdNumberAndDateOperationBetween(
                    identityNumber, fromDate, toDate), Transaction.class);
  }


}
