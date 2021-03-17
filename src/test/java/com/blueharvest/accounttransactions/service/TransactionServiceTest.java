package com.blueharvest.accounttransactions.service;

import com.blueharvest.accounttransactions.entity.Transaction;
import com.blueharvest.accounttransactions.repository.CustomerRepository;
import com.blueharvest.accounttransactions.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    private TransactionService transactionService;

    @BeforeEach
    void setMockOutput() {
        transactionService = new TransactionService(transactionRepository, customerRepository);
    }

    @Test
    public void testGetAllTransactions() {

        Transaction transaction = new Transaction();
        transaction.setTransactionId(Long.valueOf("1212"));
        transaction.setTransactionAmount(BigDecimal.valueOf(102));
        List<Transaction> transactionList = Arrays.asList(transaction);

        when(transactionService.getAllTransactions()).thenReturn(transactionList);

        assertEquals(transactionService.getAllTransactions().size(), transactionList.size());
        assertEquals(transactionService.getAllTransactions().get(0).getTransactionId(), Long.valueOf("1212"));
    }

    //TODO add more tests for TransactionService

}
