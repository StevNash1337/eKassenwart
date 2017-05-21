package de.naju.ahlen.persistence.repositories;

import de.naju.ahlen.persistence.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Steffen on 18.05.2017.
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
