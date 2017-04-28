package de.naju.ahlen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Steffen on 28.04.2017.
 */
@Entity
public class CashAccount extends AbstractAccount{

    @Id
    @GeneratedValue
    private Long id;
}
