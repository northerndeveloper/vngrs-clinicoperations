package com.vngrs.assignment.repository;

import com.vngrs.assignment.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alperkopuz
 * Transaction Repository class to manage DB operations for Transaction
 */
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
