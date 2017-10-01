package de.naju.ahlen.persistence.model.enums;

public enum DocumentFormat {

    PDF,
    PNG,
    JPG;

    @Override
    public String toString() {
        if (this.name().equals("PDF")) {
            return "PDF";
        } else if (this.name().equals("PNG")) {
            return "PNG";
        } else if (this.name().equals("JPG")) {
            return "JPG";
        }
        return "";
    }

    public String extension() {
        if (this.name().equals("PDF")) {
            return "pdf";
        } else if (this.name().equals("PNG")) {
            return "png";
        } else if (this.name().equals("JPG")) {
            return "jpg";
        }
        return "";
    }
}
