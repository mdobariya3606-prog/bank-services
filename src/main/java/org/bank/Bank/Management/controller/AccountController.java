package org.bank.Bank.Management.controller;

import org.bank.Bank.Management.dto.CreateAccountRequest;
import org.bank.Bank.Management.model.*;
import org.bank.Bank.Management.service.AccountService;
import org.bank.Bank.Management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Account> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Account createAccount(@RequestBody CreateAccountRequest account) {
        return service.createAccount(account);
    }

    @PatchMapping("/withdraw/{accountNumber}/{amount}")
    public Account withdraw(@PathVariable String accountNumber,
                           @PathVariable double amount) {
        return service.withdraw(accountNumber, amount);
    }

    @PatchMapping("/deposit/{accountNumber}/{amount}")
    public Account deposit(@PathVariable String accountNumber,
                           @PathVariable double amount) {
        return service.deposit(accountNumber, amount);
    }

    @PatchMapping("/transfer/{sender}/{receiver}/{amount}")
    public void transfer(@PathVariable String sender,
                         @PathVariable String receiver,
                         @PathVariable double amount) {
        service.transfer(sender, receiver, amount);
    }
}
