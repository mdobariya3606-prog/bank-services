package org.bank.Bank.Management.generator.repository;

import org.bank.Bank.Management.generator.model.DebitCardSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardSequenceRepository extends JpaRepository<DebitCardSequence, Long> {
}
