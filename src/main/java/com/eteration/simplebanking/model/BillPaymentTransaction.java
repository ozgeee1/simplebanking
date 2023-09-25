package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BillPaymentTransaction extends WithdrawalTransaction{

    private String payee;
    private String accountNumber;

    public BillPaymentTransaction() {
    }

    public BillPaymentTransaction(String payee, String accountNumber,double amount) {
        super(amount);
        this.payee = payee;
        this.accountNumber = accountNumber;
    }
}
