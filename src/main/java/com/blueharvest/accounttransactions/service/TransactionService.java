package com.blueharvest.accounttransactions.service;

import com.blueharvest.accounttransactions.entity.Account;
import com.blueharvest.accounttransactions.entity.Customer;
import com.blueharvest.accounttransactions.entity.Transaction;
import com.blueharvest.accounttransactions.exception.TransactionServiceException;
import com.blueharvest.accounttransactions.repository.CustomerRepository;
import com.blueharvest.accounttransactions.repository.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Transaction Service class used for Transaction Operations
 */
@Service
public class TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionService.class);

    private TransactionRepository transactionRepository;

    private CustomerRepository customerRepository;

    public TransactionService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    public List<Transaction> getAllTransactions() {
        logger.debug("All Transactions are listed");
        return transactionRepository.findAll();
    }

    public List<Transaction> findAllTransactionsOfACustomer(Long customerID) throws TransactionServiceException {

        logger.debug("Finding all transactions of a customer services is called");

        Customer customer = customerRepository.findById(customerID).get();
        if (customer == null) {
            throw new TransactionServiceException("Customer could not found in the DB with the provided customerID. Please try " +
                    "another customerID ");
        }

        List<Transaction> transactionList = collectTransactionsFromAccountOfCustomer(customer);
        logger.debug("All transactions are found with the provided customerID");
        return transactionList;
    }

    /**
     * This method is used to collect all transactions from accounts of customer
     * @param customer
     * @return
     */
    private List<Transaction> collectTransactionsFromAccountOfCustomer(Customer customer) {

        logger.debug("Collecting transactions from account of customer is initialized ");
        List<Account> accountList = customer.getAccountList();
        List<Transaction> transactionList = new ArrayList<>();
        for (Account account : accountList) {
            transactionList.addAll(account.getTransactionList());
        }
        logger.debug("All Transactions are added into the Transaction List");
        return transactionList;
    }
}
