package com.blueharvest.accounttransactions.repository;

import com.blueharvest.accounttransactions.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository class of Account which is used for ORM Operations of Account
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
