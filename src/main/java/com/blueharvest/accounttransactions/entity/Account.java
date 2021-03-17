package com.blueharvest.accounttransactions.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Account Entity which keeps all the requied information of an Account of a user
 */
@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactionList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
