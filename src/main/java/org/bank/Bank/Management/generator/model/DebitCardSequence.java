package org.bank.Bank.Management.generator.model;

import jakarta.persistence.*;

@Entity
@Table(name = "debit_card_sequence")
public class DebitCardSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
