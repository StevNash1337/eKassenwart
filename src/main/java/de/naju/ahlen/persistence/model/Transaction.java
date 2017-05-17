package de.naju.ahlen.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author Lucas
 */

@Entity
public class Transaction extends AbstractEntity {

    @Column(name = "account")
    private AbstractAccount account;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "category")
    private Category category;

    @Column(name = "reference")
    private String reference;

    @Column(name = "amount")
    private double amount;

    @Column(name = "person")
    private Person person;

    @Column(name = "comment")
    private String comment;

    public AbstractAccount getAccount() {
        return account;
    }

    public void setAccount(AbstractAccount account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
