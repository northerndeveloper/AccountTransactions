package com.blueharvest.accounttransactions.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Customer entity which keeps all required information of a Customer
 */
@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long customerID;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accountList;

}
