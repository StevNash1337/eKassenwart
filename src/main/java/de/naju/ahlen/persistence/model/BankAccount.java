package de.naju.ahlen.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Lucas
 */

@Entity
public class BankAccount extends AbstractAccount {

    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "bank_number")
    private int bankNumber;

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
