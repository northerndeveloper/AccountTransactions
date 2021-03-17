package com.blueharvest.accounttransactions.rest.controller;

import com.blueharvest.accounttransactions.entity.Account;
import com.blueharvest.accounttransactions.exception.AccountRestControllerException;
import com.blueharvest.accounttransactions.exception.AccountServiceException;
import com.blueharvest.accounttransactions.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

/**
 * Controller class for Account which is used for Rest Operations and Frontend for Account management
 * such like getting & creating account
 */
@Controller
public class AccountRestController {

    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    private static final Logger logger = LogManager.getLogger(AccountRestController.class);

    /**
     * Returns account page which resides under resources/templates/
     *
     * @return
     */
    @GetMapping("/account")
    public String getAccount() {
        return "account";
    }

    /**
     * Creates an account in the DB with the given customerID and initialCredit by the user. Used by Rest Service
     *
     * @param customerID
     * @param initialCredit
     * @return
     * @throws AccountRestControllerException
     * @throws AccountServiceException
     */
    @PostMapping("/createaccount")
    public ResponseEntity<Account> createAccount(@RequestParam(value = "customerid") Long customerID,
                                                 @RequestParam(value = "initialcredit") BigDecimal initialCredit) throws AccountRestControllerException, AccountServiceException {
        //TODO manage redirectattributes
        //TODO initialcredit verilmese de olur bunu lütfen kontrol et

        logger.info("Create Account is created. Customer ID is " + customerID + " Initial Credit is " + initialCredit);
        if (initialCredit.compareTo(BigDecimal.ZERO) < 0) {
            //  redirectAttributes.addFlashAttribute("alertClass", "alert-danger"); TODO yaratıldı veya yaratılamadı
            // uyarılarını vermen lazım
            logger.error("Initial Credit should not be smaller than 0");
            throw new AccountRestControllerException("Initial Credit should not be smaller than 0");
        }

        Account account = accountService.createAccount(customerID, initialCredit);
        if (account == null) {
            logger.error("Unable to create account. Please try again later");
            throw new AccountRestControllerException("Unable to create account. Please try again later");
        }

        logger.info("Account is created successfully. Created acount ID is " + account.getAccountId());
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    /**
     * Creates an account in the DB with the given customerID and initialCredit by the user. Used by the Frontend
     * Application
     *
     * @param customerID
     * @param initialCredit
     * @param redirectAttributes
     * @return
     * @throws AccountRestControllerException
     * @throws AccountServiceException
     */
    @PostMapping("/account")
    public String createAccount(@RequestParam(value = "customerid") Long customerID,
                                @RequestParam(value = "initialcredit", defaultValue =  "0", required = false) BigDecimal initialCredit,
                                RedirectAttributes redirectAttributes) throws AccountRestControllerException, AccountServiceException {
        //TODO manage redirectattributes

        logger.info("Create Account is created. Customer ID is " + customerID + " Initial Credit is " + initialCredit);
        if ( 0 < BigDecimal.ZERO.compareTo(initialCredit) ) {
            logger.error("Initial Credit should not be smaller than 0");
            redirectAttributes.addFlashAttribute("error", "Everything did went just fine.");  //TODO
            throw new AccountRestControllerException("Initial Credit should not be smaller than 0");
        }

        //TODO lütfen loglamaları ekle ve hata oluştuğunda exception case fırlatılmasını da buradan yap
        redirectAttributes.addFlashAttribute("success", "Everything went just fine.");

        Account account = accountService.createAccount(customerID, initialCredit);
        if (account == null) {
            logger.error("Unable to create account. Please try again later");
            throw new AccountRestControllerException("Unable to create account. Please try again later");
        }

        logger.info("Account is created successfully. Created acount ID is " + account.getAccountId());
        return "account";
    }
}




