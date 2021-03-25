package com.blueharvest.accounttransactions.service;

import com.blueharvest.accounttransactions.entity.Customer;
import com.blueharvest.accounttransactions.exception.CustomerServiceException;
import com.blueharvest.accounttransactions.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Customer Service class used for Customer Operations
 */
@Service
public class CustomerService {

    private static final Logger logger = LogManager.getLogger(CustomerService.class);

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerByID(Long customerID) throws CustomerServiceException {

        logger.debug("Customer is found by ID " + customerID);
        Optional<Customer> customer = customerRepository.findById(customerID);
        if(customer.isEmpty()) {
            logger.error("Customer could not be find with the customerID " + customerID);
            throw new CustomerServiceException("Customer could not be find with the customerID " + customerID);
        }
        return customer.get();
    }
}
