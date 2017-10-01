package de.naju.ahlen.persistence.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import de.naju.ahlen.persistence.model.enums.CategoryType;

@DatabaseTable(tableName = "categories")
public class Category {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String value;

    @DatabaseField(canBeNull = false)
    private CategoryType type;

    Category() {

    }

    public Category(String value, CategoryType type) {
        this.value = value;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (value != null) {
            return value;
        }
        return "";
    }
}
