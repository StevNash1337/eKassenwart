package de.naju.ahlen.persistence.model.enums;

public enum AccountType {

    CASHACCOUNT,
    BANKACCOUNT,
    ONLINEACCOUNT;

    @Override
    public String toString() {
        if (this.name().equals("CASHACCOUNT")) {
            return "Barkonto";
        } else if (this.name().equals("BANKACCOUNT")) {
            return "Bankkonto";
        } else if (this.name().equals("ONLINEACCOUNT")) {
            return "Onlinekonto";
        }
        return "";
    }
}
