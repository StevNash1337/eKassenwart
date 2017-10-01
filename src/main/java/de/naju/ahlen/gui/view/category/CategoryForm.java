package de.naju.ahlen.gui.view.category;

import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.ComboBox;
import de.naju.ahlen.gui.view.base.BaseForm;
import de.naju.ahlen.persistence.model.Category;
import de.naju.ahlen.persistence.model.enums.CategoryType;
import org.vaadin.viritin.fields.MTextField;

import java.util.LinkedList;
import java.util.List;

public class CategoryForm extends BaseForm<Category> {

    private MTextField value = new MTextField("Name");
    private ComboBox<CategoryType> type = new ComboBox<>("Typ");

    public CategoryForm(CategoryController controller) {
        super(controller);

        List<CategoryType> types = new LinkedList<>();
        types.add(CategoryType.INCOME);
        types.add(CategoryType.EXPENSE);

        type.setItems(types);
        type.setEmptySelectionAllowed(false);
        type.setTextInputAllowed(false);

        SerializablePredicate<String> nameNotEmptyPredicate =
                (SerializablePredicate<String>) v -> !value.isEmpty();

        SerializablePredicate<CategoryType> typeNotEmptyPredicate =
                (SerializablePredicate<CategoryType>) v -> type.getSelectedItem().isPresent();

        SerializablePredicate<Category> typeNotExistsPredicate =
                (SerializablePredicate<Category>) v ->
                        controller.existsType(type.getSelectedItem(), value.getValue());

        binder.forField(value)
                .withValidator(nameNotEmptyPredicate, "Name darf nicht leer sein")
                .bind(Category::getValue, Category::setValue);

        binder.forField(type)
                .withValidator(typeNotEmptyPredicate, "Typ auswählen")
                .bind(Category::getType, Category::setType);

        binder.withValidator(typeNotExistsPredicate, "Kategorie exisitert bereits");

        bind();
    }
}
