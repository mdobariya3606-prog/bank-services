package org.bank.Bank.Management.service;

import jakarta.transaction.Transactional;
import org.bank.Bank.Management.dto.CreateAccountRequest;
import org.bank.Bank.Management.exception.AccountNotFoundException;
import org.bank.Bank.Management.exception.InsufficientBalanceException;
import org.bank.Bank.Management.model.Account;
import org.bank.Bank.Management.generator.model.AccountSequence;
import org.bank.Bank.Management.enums.TransactionType;
import org.bank.Bank.Management.repository.AccountRepository;
import org.bank.Bank.Management.generator.repository.AccountSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private AccountSequenceRepository accountSequenceRepo;

    @Autowired
    private TransactionService transactionService;

    public List<Account> getAll() {
        return accountRepo.findAll();
    }

    public Account createAccount(CreateAccountRequest account) {
        if (account.getBalance() < 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Balance Can't be < 0");
        }
        Account newAccount = new Account();

        newAccount.setHolderName(account.getName());
        newAccount.setAccountNumber(generateAccountNo());
        newAccount.setBalance(account.getBalance());

        return accountRepo.save(newAccount);
    }

    @Transactional
    public Account withdraw(String accountNumber, double amount) {
        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));

//        Consistency
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException();
        }

        account.setBalance(account.getBalance() - amount);
        transactionService.createTransaction(accountNumber, TransactionType.withdraw, amount);
        return accountRepo.save(account);
    }

    @Transactional
    public Account deposit(String accountNumber, double amount) {
        if (amount < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid amount");
        }
        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));

        account.setBalance(account.getBalance() + amount);

        transactionService.createTransaction(accountNumber, TransactionType.deposit, amount);
        return accountRepo.save(account);
    }

    @Transactional
    public void transfer(String sender, String receiver, double amount) {
        Account senderAccount = accountRepo.findByAccountNumber(sender)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender Not Found !!"));

        Account recieverAccount = accountRepo.findByAccountNumber(receiver)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver Not Found !!"));

        if (senderAccount.getBalance() < amount) {
            throw new InsufficientBalanceException();
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        recieverAccount.setBalance(recieverAccount.getBalance() + amount);

        accountRepo.save(senderAccount);
        accountRepo.save(recieverAccount);
    }

    private String generateAccountNo() {
        AccountSequence sequence = accountSequenceRepo.save(new AccountSequence());
        long id = sequence.getId();

        String sequencePart = String.format("%05d", id);
        return "10126" + sequencePart;
    }
}
