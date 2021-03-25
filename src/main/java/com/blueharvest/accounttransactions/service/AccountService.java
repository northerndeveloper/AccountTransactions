package com.blueharvest.accounttransactions.service;

import com.blueharvest.accounttransactions.entity.Account;
import com.blueharvest.accounttransactions.entity.Customer;
import com.blueharvest.accounttransactions.entity.Transaction;
import com.blueharvest.accounttransactions.exception.AccountServiceException;
import com.blueharvest.accounttransactions.repository.AccountRepository;
import com.blueharvest.accounttransactions.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Account Service class used for Account Operations
 */
@Service
@Transactional
public class AccountService {

    private static final Logger logger = LogManager.getLogger(AccountService.class);

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Creates Account by Customer ID and Initial Credit
     *
     * @param customerID
     * @param initialCredit
     * @return
     */
    public Account createAccount(Long customerID, BigDecimal initialCredit) throws AccountServiceException {

        logger.debug("Create account servie is called with the provided Customer Id = " + customerID + " and initialCredit "
                + initialCredit);

        Optional<Customer> customer = customerRepository.findById(customerID);
        if (customer.isEmpty()) {
            logger.error(customerID + " does not reside at DB .Change the customerID");
            throw new AccountServiceException("Customer ID provided does not reside at DB. Please check customerID" +
                    "again");
        }

        logger.debug("Customer is found by ID " + customer.get().getCustomerID());

        Account account = new Account();
        account.setCustomer(customer.get());
        account.setBalance(initialCredit);
        logger.debug("Balance for Transaction is " + initialCredit);

        if (initialCredit != null) {
            List<Transaction> transactionList = createTransactionList(initialCredit, account);
            account.setTransactionList(transactionList);
        }
        return accountRepository.save(account);
    }

    /**
     * Creates Transaction List with initialCredit and Account
     *
     * @param initialCredit
     * @param account
     * @return
     */
    public List<Transaction> createTransactionList(BigDecimal initialCredit, Account account) {

        logger.debug("Transaction List creation has been initialized with the initialCredit " + initialCredit +
                " with the account ID " + account.getAccountId());
        List<Transaction> transactionList = new ArrayList<>();
        if (initialCredit.compareTo(BigDecimal.ZERO) != 0) {
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setTransactionAmount(initialCredit);
            transactionList.add(transaction);
        }
        logger.debug("Transaction List is created successfully");
        return transactionList;
    }

    /**
     * Creates Total Balance of Customer's Accounts
     *
     * @param accountList
     * @return
     */
    public BigDecimal getTotalBalanceOfCustomer(List<Account> accountList) {

        BigDecimal totalBalance = BigDecimal.ZERO;

        for (Account account : accountList) {
            totalBalance = totalBalance.add(account.getBalance());
        }
        logger.debug("Total Balance of an account is " + totalBalance);
        return totalBalance;
    }
}
