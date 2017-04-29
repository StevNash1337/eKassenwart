package de.naju.ahlen.model;

import de.naju.ahlen.model.enums.AmountType;
import de.naju.ahlen.model.enums.TransactionCategory;

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
    private String name;
    private Member member;
    private Date date;
    private double amount;
    private AmountType type;
    private TransactionCategory category;
    private String comment;

    public Transaction(String name, Member member, Date date, double amount, AmountType type, TransactionCategory category, String comment) {
        this.name = name;
        this.member = member;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.comment = comment;
    }

    public String getFullName(){
        return getMember().getFirstName() + " " + getMember().getLastName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AmountType getType() {
        return type;
    }

    public void setType(AmountType type) {
        this.type = type;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
