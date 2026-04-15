package org.bank.Bank.Management.controller;

import org.bank.Bank.Management.dto.TransactionSummaryDTO;
import org.bank.Bank.Management.model.Transaction;
import org.bank.Bank.Management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping
    public List<Transaction> getAll() {
        return service.findAll();
    }

    @GetMapping("/statement/{accountNumber}")
    public List<TransactionSummaryDTO> getStatement(@PathVariable String accountNumber) {
        return service.findByAccountNumber(accountNumber);
    }
}
