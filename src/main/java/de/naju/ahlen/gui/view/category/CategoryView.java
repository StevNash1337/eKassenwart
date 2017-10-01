package de.naju.ahlen.gui.view.category;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import de.naju.ahlen.gui.view.base.BaseController;
import de.naju.ahlen.gui.view.base.BaseView;
import de.naju.ahlen.persistence.model.Category;
import de.naju.ahlen.persistence.model.project.Project;
import org.vaadin.viritin.grid.MGrid;

@UIScope
@SpringView
public class CategoryView extends BaseView<Category> {

    public CategoryView(CategoryController controller) {
        super(controller, "Kategorien");
    }

    @Override
    protected String getAddToolTip() {
        return "Neue Kategorie erstellen";
    }

    @Override
    protected String getEditToolTip() {
        return "Kategorie bearbeiten";
    }

    @Override
    protected String getDeleteToolTip() {
        return "Kategorie löschen";
    }

    @Override
    protected String getDeleteConfirmationText() {
        return "Sind Sie sicher, dass Sie die Kategorie löschen wollen?";
    }

    @Override
    protected String getWindowCaption() {
        return "Kategorie löschen";
    }

    @Override
    protected void initializeGrid() {
        grid = new MGrid<>(Category.class)
                .withProperties("value", "type")
                .withColumnHeaders("Name", "Typ")
                .withFullWidth()
                .withFullHeight();
    }
}
