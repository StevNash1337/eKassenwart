package de.naju.ahlen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Sparkasse, Paypal, ...
 * Created by Steffen on 28.04.2017.
 */
@Entity
public class OnlineAccount extends AbstractAccount{

    @Id
    @GeneratedValue
    private Long id;
    private int accountNumber; //KTO
    private int bankNumber; //BLZ

    public OnlineAccount(String name, double credit, int accountNumber, int bankNumber) {
        this.setName(name);
        this.setCredit(credit);
        this.accountNumber = accountNumber;
        this.bankNumber = bankNumber;
    }

    public Long getId() {
        return id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(int bankNumber) {
        this.bankNumber = bankNumber;
    }
}
