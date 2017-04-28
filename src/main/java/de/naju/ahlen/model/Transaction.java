package de.naju.ahlen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Steffen on 25.04.2017.
 */
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    private Member member;
    private Date date;
    private double amount;
    private AmountType type;
}
