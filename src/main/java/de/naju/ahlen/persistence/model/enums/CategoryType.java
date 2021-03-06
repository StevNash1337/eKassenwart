package de.naju.ahlen.persistence.model.enums;

public enum CategoryType {

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
