package de.naju.ahlen.persistence.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;

@DatabaseTable(tableName = "bank_accounts")
public class BankAccount extends AbstractAccount {

    @DatabaseField(canBeNull = false)
    private int bankNumber;

    @DatabaseField(canBeNull = false)
    private int accountNumber;

    @DatabaseField(canBeNull = false)
    private String iban;

    public BankAccount() {

    }

    public BankAccount(String name, BigDecimal startAmount, int bankNumber, int accountNumber, String iban) {
        this.name = name;
        this.startAmount = startAmount;
        this.bankNumber = bankNumber;
        this.accountNumber = accountNumber;
        this.iban = iban;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(int bankNumber) {
        this.bankNumber = bankNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
