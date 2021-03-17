package com.blueharvest.accounttransactions.service;

import com.blueharvest.accounttransactions.entity.Account;
import com.blueharvest.accounttransactions.entity.Customer;
import com.blueharvest.accounttransactions.entity.Transaction;
import com.blueharvest.accounttransactions.exception.AccountServiceException;
import com.blueharvest.accounttransactions.repository.AccountRepository;
import com.blueharvest.accounttransactions.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerRepository customerRepository;

    private AccountService accountService;

    private int customerID = 1212;

    @BeforeEach
    void setMockOutput() {
        accountService = new AccountService(accountRepository, customerRepository);
    }

    @Test()
    public void testCreateAccountHandleIfNoCustomer() {

        Account account = new Account();
        account.setBalance(BigDecimal.ONE);
        account.setCustomer(null);
        List<Transaction> transactionList = accountService.createTransactionList(BigDecimal.ONE, account);
        account.setTransactionList(transactionList);
        when(accountRepository.save(account)).thenReturn(account);


        Assertions.assertThrows(AccountServiceException.class, () -> {
            accountService.createAccount(Long.valueOf(customerID), BigDecimal.ONE);
        });
    }


    @Test
    public void testCreateAccountSuccessfull() throws AccountServiceException {

        Customer customer = new Customer();
        customer.setCustomerID(Long.valueOf(customerID));
        customer.setCustomerName("alper");
        customer.setCustomerSurname("kopuz");
        customer.setAccountList(new ArrayList<>());

        when(customerRepository.findById(Long.valueOf(customerID))).thenReturn(java.util.Optional.of(customer));

        Account account = new Account();
        account.setBalance(BigDecimal.ONE);
        account.setCustomer(customer);
        List<Transaction> transactionList = accountService.createTransactionList(BigDecimal.ONE, account);
        account.setTransactionList(transactionList);
        when(accountRepository.save(account)).thenReturn(account);

        accountService.createAccount(Long.valueOf(customerID), BigDecimal.ONE);
    }

    @Test
    public void testTotalBalanceOfCustomer() {

        List<Account> accountList = new ArrayList<>();

        Customer customer = new Customer();
        customer.setCustomerID(Long.valueOf(customerID));
        customer.setCustomerName("alper");
        customer.setCustomerSurname("kopuz");
        customer.setAccountList(new ArrayList<>());

        Account account = new Account();
        account.setAccountId(Long.parseLong("123"));
        account.setCustomer(customer);
        account.setBalance(BigDecimal.valueOf(130));

        accountList.add(account);

        account = new Account();
        account.setAccountId(Long.parseLong("124"));
        account.setCustomer(customer);
        account.setBalance(BigDecimal.valueOf(150));

        accountList.add(account);

        assertEquals(accountService.getTotalBalanceOfCustomer(accountList), BigDecimal.valueOf(280));
    }

    @Test
    public void testCreateTransactionList() {

        List<Account> accountList = new ArrayList<>();

        Customer customer = new Customer();
        customer.setCustomerID(Long.valueOf(customerID));
        customer.setCustomerName("alper");
        customer.setCustomerSurname("kopuz");
        customer.setAccountList(new ArrayList<>());

        Account account = new Account();
        account.setAccountId(Long.parseLong("123"));
        account.setCustomer(customer);
        account.setBalance(BigDecimal.valueOf(130));

        accountList.add(account);

        List<Transaction> transactionList = accountService.createTransactionList(BigDecimal.valueOf(100), account);

        assertEquals(transactionList.size(), 1);
        assertEquals(transactionList.get(0).getAccount(), account);
        assertEquals(transactionList.get(0).getTransactionAmount(), BigDecimal.valueOf(100));

    }

}