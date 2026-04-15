package org.bank.Bank.Management.service;

import jakarta.transaction.Transactional;
import org.bank.Bank.Management.dto.TransactionSummaryDTO;
import org.bank.Bank.Management.enums.TransactionType;
import org.bank.Bank.Management.model.Transaction;
import org.bank.Bank.Management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Transactional
    public void createTransaction(String accountNumber, TransactionType type, double amount) {
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setDate(LocalDate.now());

        transactionRepo.save(transaction);
    }

    public List<TransactionSummaryDTO> getTransaction(String accountNumber) {
        return transactionRepo.findAllByAccountNumber(accountNumber);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public List<TransactionSummaryDTO> findByAccountNumber(String accountNumber) {
        return transactionRepo.findAllByAccountNumber(accountNumber);
    }

    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }
}
