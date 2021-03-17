package com.blueharvest.accounttransactions.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Transaction entity which keeps all requiered information of  a proper Transaction
 */
@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_amount")
    @NotNull
    private BigDecimal transactionAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


}
