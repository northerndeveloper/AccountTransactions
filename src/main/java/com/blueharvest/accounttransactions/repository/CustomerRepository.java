package com.blueharvest.accounttransactions.repository;

import com.blueharvest.accounttransactions.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository class of Customer which is used for ORM Operations of Customer
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
