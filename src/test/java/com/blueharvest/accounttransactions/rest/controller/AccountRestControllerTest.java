package com.blueharvest.accounttransactions.rest.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAccount() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/account").toString(), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getStatusCodeValue(), 200);
        assertThat(response.getBody()).contains("Customer ID");
    }

    @Test
    public void testGetAccountWrongLinkFails() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/accounts").toString(), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(response.getStatusCodeValue(), 404);
        assertThat(response.getBody()).contains("Not Found");
    }

    @Test
    public void testCreateAccount() throws MalformedURLException {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> request = new HttpEntity<String>("");

        ResponseEntity<String> response = restTemplate.postForEntity(
                new URL("http://localhost:" + port + "/createaccount?customerid=323132&initialcredit=10").toString(), request, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getStatusCodeValue(), 201);
        assertThat(response.getBody()).contains("\"balance\":10");
    }

    @Test
    public void testCreateAccountFailsWrongCustomer() {

        RestTemplate restTemplate = new RestTemplate();


        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            ResponseEntity<String> response = restTemplate.getForEntity(
                    new URL("http://localhost:" + port + "/createaccount?customerid=323000initialcredit=10").toString(), String.class);
        });
    }
}