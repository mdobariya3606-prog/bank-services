package org.bank.Bank.Management.generator.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account_sequence")
public class AccountSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
