package org.bank.Bank.Management.controller;

import org.bank.Bank.Management.dto.DebitCardRequestDTO;
import org.bank.Bank.Management.model.DebitCard;
import org.bank.Bank.Management.service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class DebitCardController {
    @Autowired
    DebitCardService service;

    @GetMapping
    public List<DebitCard> getAll() {
        return service.getAll();
    }

    @PostMapping
    public DebitCard create(@RequestBody DebitCardRequestDTO debitCard) {
        return service.createDebitCard(debitCard);
    }
}
