package com.blueharvest.accounttransactions.repository;

import com.blueharvest.accounttransactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository class of Transaction which is used for ORM Operations of Transaction
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
