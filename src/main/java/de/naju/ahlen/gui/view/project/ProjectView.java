package de.naju.ahlen.gui.view.project;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import de.naju.ahlen.gui.view.base.BaseView;
import de.naju.ahlen.persistence.model.project.Project;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.grid.MGrid;


@UIScope
@SpringView
public class ProjectView extends BaseView<Project> {

    private MButton bActivate;

    public ProjectView(ProjectController controller) {
        super(controller, "Projekte");

        bActivate = new MButton(VaadinIcons.CHECK);
        bActivate.addClickListener((Button.ClickListener) event -> controller.buttonActivateClicked());
        bActivate.setDescription(getSelectConfirmationToolTip());
        buttonLayout.addComponent(bActivate, 3);
    }

    public void setButtonActivateEnabled(boolean b) {
        bActivate.setEnabled(b);
    }

    @Override
    protected String getAddToolTip() {
        return "Neues Projekt erstellen";
    }

    @Override
    protected String getEditToolTip() {
        return "Projekt bearbeiten";
    }

    @Override
    protected String getDeleteToolTip() {
        return "Projekt löschen";
    }

    @Override
    protected String getDeleteConfirmationText() {
        return "Sind Sie sicher, dass Sie das gesamte Projekt löschen wollen?";
    }

    @Override
    protected String getWindowCaption() {
        return "Projekt löschen";
    }

    protected String getSelectConfirmationToolTip() {
        return "Projekt auswählen";
    }

    @Override
    protected void initializeGrid() {
        grid = new MGrid<>(Project.class)
                .withProperties("fullName")
                .withColumnHeaders("")
                .withFullWidth()
                .withFullHeight();
    }
}
