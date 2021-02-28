package com.vngrs.assignment.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author alperkopuz
 * Transaction Document required attributes to create a Transaction Entity
 */
@Document
@Data
@Builder
public class Transaction {

    @Id
    private String transactionId;

    private Double cancellationFee;

    private Appointment appointment;

}
