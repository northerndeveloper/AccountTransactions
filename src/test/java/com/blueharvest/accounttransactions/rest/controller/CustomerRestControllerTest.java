package com.blueharvest.accounttransactions.rest.controller;

import com.blueharvest.accounttransactions.exception.CustomerServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCustomerByID() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/showcustomer?customerid=323132").toString(), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getStatusCodeValue(), 200);
        assertThat(response.getBody()).contains("\"customerID\":323132");
    }

    @Test
    public void testGetCustomer() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/customer").toString(), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getStatusCodeValue(), 200);
    }

   /* @Test  TODO
    public void testGetCustomerByIDFails() {

        Throwable exception = assertThrows(CustomerServiceException.class, () -> {
            ResponseEntity<String> response = restTemplate.getForEntity(
                    new URL("http://localhost:" + port + "/showcustomer?customerid=12345").toString(), String.class);
        });



    }
*/


}
