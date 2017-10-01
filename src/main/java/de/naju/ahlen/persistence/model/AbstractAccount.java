package de.naju.ahlen.persistence.model;

import com.j256.ormlite.field.DatabaseField;

import java.math.BigDecimal;

public abstract class AbstractAccount {

    @DatabaseField(generatedId = true)
    protected long id;

    @DatabaseField(canBeNull = false)
    protected String name;

    @DatabaseField(canBeNull = false)
    protected BigDecimal startAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(BigDecimal startAmount) {
        this.startAmount = startAmount;
    }

    @Override
    public String toString() {
        if (name != null) {
            return name;
        }
        return "";
    }
}
