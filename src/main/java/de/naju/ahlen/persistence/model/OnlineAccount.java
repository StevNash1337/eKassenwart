package de.naju.ahlen.persistence.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;

@DatabaseTable(tableName = "online_accounts")
public class OnlineAccount extends AbstractAccount {

    @DatabaseField(canBeNull = false)
    private String email;

    public OnlineAccount() {

    }

    public OnlineAccount(String name, BigDecimal startAmount, String email) {
        this.name = name;
        this.startAmount = startAmount;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
