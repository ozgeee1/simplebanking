package com.eteration.simplebanking.services;

import com.eteration.simplebanking.exceptions.AccountNotFoundException;
import com.eteration.simplebanking.exceptions.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.TransactionType;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.response.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

// This class is a place holder you can change the complete implementation
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
         return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException("Account not found with this account number: "+accountNumber));
    }


    public TransactionStatus debit(String accountNumber, WithdrawalTransaction transaction)
            throws AccountNotFoundException, InsufficientBalanceException {
        transaction.setDate(LocalDateTime.now());
        transaction.setType(TransactionType.WithdrawalTransaction);
        return transactionOperation(findAccount(accountNumber),transaction);
    }

    public TransactionStatus credit(String accountNumber, DepositTransaction transaction)
            throws AccountNotFoundException, InsufficientBalanceException {
        transaction.setDate(LocalDateTime.now());
        transaction.setType(TransactionType.DepositTransaction);
        return transactionOperation(findAccount(accountNumber),transaction);

    }
    @Transactional
    public TransactionStatus transactionOperation(Account account, Transaction transaction) throws InsufficientBalanceException {
        String approvalCode = UUID.randomUUID().toString();
        TransactionStatus transactionStatus = TransactionStatus.builder().status("OK").approvalCode(approvalCode).build();
        transaction.setAccount(account);
        transaction.setApprovalCode(approvalCode);
        account.post(transaction);
        accountRepository.save(account);
        return transactionStatus;

    }

}
