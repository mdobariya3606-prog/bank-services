package org.bank.Bank.Management.dto;

import org.bank.Bank.Management.enums.TransactionType;

import java.time.LocalDate;

public interface StatementDTO {
    public TransactionType getType();
    public double getAmount();
    public LocalDate getDate();
}
