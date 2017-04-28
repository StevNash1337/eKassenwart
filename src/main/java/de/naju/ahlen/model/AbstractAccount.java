package de.naju.ahlen.model;

/**
 * Spiegelt den Typen eines Kontos wieder.
 * Created by Steffen on 27.04.2017.
 */
public abstract class AbstractAccount {

    private String name;
    private double credit;

    public AbstractAccount(){
        this("", 0);
    }

    public AbstractAccount(String name, double credit){
        this.name = name;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public double getCredit() {
        return credit;
    }
}
