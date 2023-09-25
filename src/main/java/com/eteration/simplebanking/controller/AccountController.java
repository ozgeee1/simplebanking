package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.exceptions.AccountNotFoundException;
import com.eteration.simplebanking.exceptions.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.response.TransactionStatus;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class is a place holder you can change the complete implementation
@RestController
@RequiredArgsConstructor
@RequestMapping("account/v1")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.findAccount(accountNumber));
    }

    @PostMapping("debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber,@RequestBody WithdrawalTransaction transaction)
            throws AccountNotFoundException, InsufficientBalanceException {
        return ResponseEntity.ok(accountService.debit(accountNumber,transaction));
    }
    @PostMapping("credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber,@RequestBody DepositTransaction transaction)
            throws AccountNotFoundException, InsufficientBalanceException {
        return ResponseEntity.ok(accountService.credit(accountNumber,transaction));
	}

    @PostMapping("bill/payment/{accountNumber}")
    public ResponseEntity<TransactionStatus> payment(@PathVariable String accountNumber,@RequestBody PhoneBillPaymentTransaction transaction)
            throws AccountNotFoundException, InsufficientBalanceException {
        return ResponseEntity.ok(accountService.debit(accountNumber,transaction));
    }

}