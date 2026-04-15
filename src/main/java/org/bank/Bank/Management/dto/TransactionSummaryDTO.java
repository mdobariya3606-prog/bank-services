package org.bank.Bank.Management.dto;

import org.bank.Bank.Management.enums.TransactionType;

import java.time.LocalDate;

public interface TransactionSummaryDTO {
    TransactionType getType();
    Double getAmount();
    LocalDate getDate();
}
