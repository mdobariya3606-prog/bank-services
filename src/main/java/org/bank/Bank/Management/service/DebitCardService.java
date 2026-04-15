package org.bank.Bank.Management.service;

import jakarta.transaction.Transactional;
import org.bank.Bank.Management.exception.AccountNotFoundException;
import org.bank.Bank.Management.generator.repository.DebitCardSequenceRepository;
import org.bank.Bank.Management.dto.DebitCardRequestDTO;
import org.bank.Bank.Management.model.Account;
import org.bank.Bank.Management.model.DebitCard;
import org.bank.Bank.Management.repository.AccountRepository;
import org.bank.Bank.Management.repository.DebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DebitCardService {
    @Autowired
    DebitCardRepository repository;

    @Autowired
    DebitCardSequenceRepository cardSequenceRepository;

    @Autowired
    AccountRepository accountRepo;

    @Transactional
    public DebitCard createDebitCard(DebitCardRequestDTO debitCard) {
        if (debitCard.getId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account id is required");
        }

        Account account = accountRepo.findById(debitCard.getId())
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(debitCard.getId())));

        if (account.getDebitCard() != null) {
             throw new ResponseStatusException(HttpStatus.CONFLICT, "Debit card already exists");
        }

        DebitCard card = new DebitCard();
        card.setAccount(account);
        card.setCardNumber(generateCardNumber(debitCard.getId()));
        card.setExpiryDate(debitCard.getExpiryDate());

        repository.save(card);
        return card;
    }

    private String generateCardNumber(long id) {
        return "8080" + "1980" + String.format("%08d", id);
    }

    public List<DebitCard> getAll() {
        return repository.findAll();
    }
}
