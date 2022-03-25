package com.bootcamp.transactions.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*** This class represent the transactions
 * of this microservice.
 *
 * @author Elvin Cardenas
 *
 *
 * @version 0.1
 */
@Document(collection = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

  @Id
  String id;

  private String typeTransaction;

  private String idNumber;

  private String accountType;

  private String accountUsed;

  private String clientIdNumber;

  private double transactionAmount;

  private String destination;

  private String transactionDescription;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime dateOperation = LocalDateTime.now();
}
