package com.blueharvest.accounttransactions.rest.controller;


import com.blueharvest.accounttransactions.entity.Transaction;
import com.blueharvest.accounttransactions.exception.TransactionServiceException;
import com.blueharvest.accounttransactions.service.CustomerService;
import com.blueharvest.accounttransactions.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Transaction Rest Controller class used for Transaction operations
 */
@Controller
public class TransactionRestController {

    private static final Logger logger = LogManager.getLogger(TransactionRestController.class);

    private TransactionService transactionService;

    private TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Returns Transactions by the Customer ID provided by the customer
     */
    @GetMapping(value = "/transactionsofcustomer")
    public ResponseEntity<List<Transaction>> getTransactionsByUserID(@RequestParam("customerid") Long customerID) throws TransactionServiceException {

        logger.info("Listing transactions of the Customer with the provided ID  " + customerID);

        List<Transaction> transactionList = transactionService.findAllTransactionsOfACustomer(customerID);

        if( transactionList.isEmpty() ) {
            logger.info("Transaction List for the provided customer ID is empty");
        }
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }
}
