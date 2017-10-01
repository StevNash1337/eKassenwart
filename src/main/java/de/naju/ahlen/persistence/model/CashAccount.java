package de.naju.ahlen.persistence.model;

import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;

@DatabaseTable(tableName = "cash_accounts")
public class CashAccount extends AbstractAccount {

    public CashAccount() {

    }

    public CashAccount(String name, BigDecimal startAmount) {
        this.name = name;
        this.startAmount = startAmount;
    }
}
