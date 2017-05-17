package de.naju.ahlen.persistence.repositories;

import de.naju.ahlen.persistence.model.CashAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lucas
 */

public interface CashAccountRepository extends JpaRepository<CashAccount, Long> {
}
