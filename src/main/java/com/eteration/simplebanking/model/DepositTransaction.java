package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation


@Entity(name = "DepositTransaction")
@NoArgsConstructor
public class DepositTransaction  extends Transaction{
    public DepositTransaction(double amount) {
        super(amount);
        setType(TransactionType.DepositTransaction);
    }
}
