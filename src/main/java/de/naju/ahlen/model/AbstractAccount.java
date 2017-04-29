package de.naju.ahlen.model;

/**
 * Spiegelt den Typen eines Kontos wieder.
 * Created by Steffen on 27.04.2017.
 */
public abstract class AbstractAccount {

    private String name;
    private double credit;

    public AbstractAccount(){}

    public String getName() {
        return name;
    }

    public double getCredit() {
        return credit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
