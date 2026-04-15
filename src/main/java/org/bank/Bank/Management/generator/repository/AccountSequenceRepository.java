package org.bank.Bank.Management.generator.repository;

import org.bank.Bank.Management.generator.model.AccountSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSequenceRepository extends JpaRepository<AccountSequence, Long> {
}
