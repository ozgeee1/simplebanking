package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.exceptions.InsufficientBalanceException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String owner;

    private String accountNumber;

    private double balance=0;

    private LocalDateTime createDate;

    //todo
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Transaction> transactions = new HashSet<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        createDate = LocalDateTime.now();
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        if(transaction.getType() == TransactionType.WithdrawalTransaction){
            WithdrawalTransaction withdrawalTransaction = (WithdrawalTransaction) transaction;
            withdraw(withdrawalTransaction.getAmount());
         }

        if(transaction.getType() == TransactionType.DepositTransaction){
            DepositTransaction depositTransaction = (DepositTransaction) transaction;
            deposit(depositTransaction.getAmount());
        }

       transactions.add(transaction);

    }

    public void deposit(double amount) {
        setBalance(balance+amount);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if(balance < amount){
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        setBalance(balance-amount);
    }
}
