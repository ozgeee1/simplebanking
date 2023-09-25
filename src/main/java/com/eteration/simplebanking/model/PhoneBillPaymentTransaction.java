package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class PhoneBillPaymentTransaction extends BillPaymentTransaction {

    public PhoneBillPaymentTransaction() {
    }

    public PhoneBillPaymentTransaction(String payee, String accountNumber, double amount) {
        super(payee, accountNumber, amount);
    }
}
