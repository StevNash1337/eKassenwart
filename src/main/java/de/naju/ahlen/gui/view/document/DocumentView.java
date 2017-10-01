package de.naju.ahlen.gui.view.document;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import de.naju.ahlen.gui.view.base.BaseView;
import de.naju.ahlen.gui.view.category.CategoryController;
import de.naju.ahlen.persistence.model.Category;
import de.naju.ahlen.persistence.model.Document;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.grid.MGrid;

@UIScope
@SpringView
public class DocumentView extends BaseView<Document> {

    private MButton bShow;

    public DocumentView(DocumentController controller) {
        super(controller, "Dokumente");

        bShow = new MButton(VaadinIcons.FILE);
        bShow.addClickListener((Button.ClickListener) event -> controller.buttonShowClicked());
        buttonLayout.addComponent(bShow, 3);

    }

    @Override
    protected String getDeleteConfirmationText() {
        return "Sind Sie sicher, dass Sie das Dokument löschen wollen?";


    }

    @Override
    protected String getWindowCaption() {
        return "Dokument löschen";
    }

    @Override
    protected void initializeGrid() {
        grid = new MGrid<>(Document.class);
        grid.removeAllColumns();
        grid.addColumn(item -> item.getName());
        grid.addColumn(item -> item.getFormat());
        grid.withColumnHeaders("Name", "Format");
        grid.setSizeFull();
    }

    public void setButtonShowEnabled(boolean b) {
        bShow.setEnabled(b);
    }
}
