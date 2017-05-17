package de.naju.ahlen.persistence.repositories;


import de.naju.ahlen.persistence.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
