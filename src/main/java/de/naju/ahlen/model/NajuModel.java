package de.naju.ahlen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.LinkedList;

/**
 * Spiegelt die Applikation wieder
 * Created by Steffen on 27.04.2017.
 */
@Entity
public class NajuModel {

    @Id
    @GeneratedValue
    private Long id;
    private LinkedList<Member> members;
    private LinkedList<AbstractAccount> accounts;

    public NajuModel(){
        this.accounts = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public LinkedList<Member> getMembers() {
        return members;
    }

    public void setMembers(LinkedList<Member> members) {
        this.members = members;
    }

    public LinkedList<AbstractAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(LinkedList<AbstractAccount> accounts) {
        this.accounts = accounts;
    }
}
