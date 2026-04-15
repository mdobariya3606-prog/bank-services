package org.bank.Bank.Management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AccountNotFoundException extends ResponseStatusException {
    public AccountNotFoundException(String accountNo) {
        super(HttpStatus.NOT_FOUND, "Account Not found with accountNo: " + accountNo);
    }
}
