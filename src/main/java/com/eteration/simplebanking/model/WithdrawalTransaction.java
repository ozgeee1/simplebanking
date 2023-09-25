package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity(name = "WithDrawalTransaction")
@NoArgsConstructor
public class WithdrawalTransaction extends Transaction{

    public WithdrawalTransaction(double amount) {
        super(amount);
        setType(TransactionType.WithdrawalTransaction);

    }

}


