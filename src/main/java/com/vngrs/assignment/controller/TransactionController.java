package com.vngrs.assignment.controller;

import com.vngrs.assignment.model.Transaction;
import com.vngrs.assignment.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author alperkopuz
 * Transaction Rest Controller class is used to get all transactions and delete all transactions
 */
@RestController
@AllArgsConstructor
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // Dependency Injection was added as constructor injection which is better by providing AllAargsConstructor of lombok
    private TransactionRepository transactionRepository;

    /**
     * Gets all the transactions
     *
     * @return
     */
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {

        logger.info("Getting all transactions");
        return transactionRepository.findAll();
    }

    /**
     * Delete all the transactions from the system
     */
    @DeleteMapping("/deletetransactions")
    public void deleteTransactions() {

        logger.info("Delete all transactions process has been removed started");

        transactionRepository.deleteAll();

        logger.info("Delete all transactions process has been completed successfully");
    }
}
