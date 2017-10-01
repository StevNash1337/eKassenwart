package de.naju.ahlen.persistence.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import de.naju.ahlen.persistence.model.enums.DocumentFormat;

@DatabaseTable(tableName = "documents")
public class Document {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private DocumentFormat format;

    public Document() {

    }

    public Document(String name, DocumentFormat format) {
        this.name = name;
        this.format = format;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentFormat getFormat() {
        return format;
    }

    public void setFormat(DocumentFormat format) {
        this.format = format;
    }

    public Document copy() {
        return new Document(name, format);
    }
}
