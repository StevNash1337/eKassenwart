package de.naju.ahlen.persistence.repositories;

import de.naju.ahlen.persistence.model.CashAccount;
import de.naju.ahlen.persistence.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lucas
 */

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
