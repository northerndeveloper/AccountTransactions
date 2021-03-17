package com.blueharvest.accounttransactions.rest.controller;

import com.blueharvest.accounttransactions.entity.Customer;
import com.blueharvest.accounttransactions.exception.CustomerServiceException;
import com.blueharvest.accounttransactions.service.AccountService;
import com.blueharvest.accounttransactions.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller used by the Rest Service and frontend to give the required customer by the user
 */
@Controller
public class CustomerRestController {

    private static final Logger logger = LogManager.getLogger(CustomerRestController.class);

    private AccountService accountService;

    private CustomerService customerService;

    public CustomerRestController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    /**
     * Returns customer entity by the customer ID provided by user. Used by Rest Service
     *
     * @param customerID
     * @return
     * @throws CustomerServiceException
     */
    @GetMapping(value = "/showcustomer")
    public ResponseEntity<Customer> getCustomerByID(@RequestParam("customerid") Long customerID) throws CustomerServiceException {

        Customer customer = customerService.findCustomerByID(customerID);
        logger.info(customer.getCustomerID() + " ID of customer has been called");

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     * Returns customer page
     *
     * @return
     */
    @GetMapping(value = "/customer")
    public String getCustomer() {
        return "customer";
    }

    /**
     * Returns customer page from the controller by the provided the customer ID
     *
     * @param customerID
     * @param model
     * @return
     * @throws CustomerServiceException
     */
    @PostMapping(value = "/customer") //TODO bu post mapping değildir bu get mappingdir.
    public String getCustomer(@RequestParam("customerid") Long customerID, Model model) throws CustomerServiceException {

        Customer customer = customerService.findCustomerByID(customerID);//TODO null case
        model.addAttribute("customer", customer);
        model.addAttribute("totalBalance", accountService.getTotalBalanceOfCustomer(customer.getAccountList()));
        logger.info(customer.getCustomerID() + " ID of customer has been called");
        return "customer";
    }

}
