package com.blueharvest.accounttransactions.service;

import com.blueharvest.accounttransactions.entity.Customer;
import com.blueharvest.accounttransactions.exception.CustomerServiceException;
import com.blueharvest.accounttransactions.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    private Long customerID = Long.valueOf("1212");

    @BeforeEach
    void setMockOutput() {
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void testFindCustomerByIDSuccessfull() throws CustomerServiceException {

        Customer customer = new Customer();
        customer.setCustomerID(Long.valueOf(customerID));
        customer.setCustomerName("alper");
        customer.setCustomerSurname("kopuz");
        customer.setAccountList(new ArrayList<>());

        when(customerRepository.findById(Long.valueOf(customerID))).thenReturn(java.util.Optional.of(customer));
        Customer createdCustomer = customerService.findCustomerByID(customerID);
        assertEquals(createdCustomer.getCustomerID(), customerID);
        assertEquals(createdCustomer.getCustomerSurname(), "kopuz");
    }

    @Test
    public void testFindCustomerByIDFail() {

        Assertions.assertThrows(CustomerServiceException.class, () -> {
            customerService.findCustomerByID(null);
        });

        Customer customer = new Customer();
        customer.setCustomerID(null);
        customer.setCustomerName("alper");
        customer.setCustomerSurname("kopuz");
        customer.setAccountList(new ArrayList<>());

        when(customerRepository.findById(Long.valueOf(customerID))).thenReturn(java.util.Optional.of(customer));

        Assertions.assertThrows(CustomerServiceException.class, () -> {
            customerService.findCustomerByID(customer.getCustomerID());
        });
    }
}
