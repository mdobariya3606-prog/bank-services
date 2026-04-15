package org.bank.Bank.Management.repository;
import org.bank.Bank.Management.dto.TransactionSummaryDTO;
import org.bank.Bank.Management.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<TransactionSummaryDTO> findAllByAccountNumber(String accountNumber);
}
