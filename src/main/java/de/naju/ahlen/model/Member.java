package de.naju.ahlen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.LinkedList;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private int mobilePhoneNumber;
    private String publicAddress;
    private String mailAddress;

    private LinkedList<Transaction> transactions;

    protected Member() {
    }

    public Member(String firstName, String lastName, int mobilePhoneNumber, String publicAddress, String mailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.publicAddress = publicAddress;
        this.mailAddress = mailAddress;

        this.transactions = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(int mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(LinkedList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return String.format("Member[id=%d, firstName='%s', lastName='%s']", id,
                firstName, lastName);
    }

}