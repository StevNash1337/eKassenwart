package de.naju.ahlen.persistence.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

@DatabaseTable(tableName = "transactions")
public class Transaction {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private Date date;

    @DatabaseField(canBeNull = false, foreign = true)
    private AbstractAccount account;

    @DatabaseField(canBeNull = false)
    private int accountType;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Category category;

    @DatabaseField(canBeNull = false)
    private String text;

    @DatabaseField(canBeNull = false)
    private BigDecimal amount;

    @DatabaseField
    private Date billDate;

    @DatabaseField(foreign = true)
    private Person person;

    @DatabaseField
    private String comment;

    public Transaction() {

    }

    public Transaction(Date date, AbstractAccount account, int accountType, Category category, String text, BigDecimal amount, Date billDate, Person person, String comment) {
        this.date = date;
        this.account = account;
        this.accountType = accountType;
        this.category = category;
        this.text = text;
        this.amount = amount;
        this.billDate = billDate;
        this.person = person;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AbstractAccount getAccount() {
        return account;
    }

    public void setAccount(AbstractAccount account) {
        this.account = account;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
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
