package de.naju.ahlen.persistence.model;

import de.naju.ahlen.persistence.model.enums.CategoryType;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Lucas
 */

@Entity
public class Category extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private CategoryType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
