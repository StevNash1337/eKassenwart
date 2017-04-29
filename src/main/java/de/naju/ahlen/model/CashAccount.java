package de.naju.ahlen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Barkasse
 * Created by Steffen on 28.04.2017.
 */
@Entity
public class CashAccount extends AbstractAccount{

    @Id
    @GeneratedValue
    private Long id;

    public CashAccount(String name, double credit){
        this.setName(name);
        this.setCredit(credit);
    }

    public Long getId() {
        return id;
    }
}
