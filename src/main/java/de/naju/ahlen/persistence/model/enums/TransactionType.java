package de.naju.ahlen.persistence.model.enums;

public enum TransactionType {

    INCOME,
    EXPENSE;

    @Override
    public String toString() {
        if (this.name().equals("INCOME")) {
            return "Einnahme";
        } else if (this.name().equals("EXPENSE")) {
            return "Ausgabe";
        }
        return "";
    }
}
