package de.naju.ahlen.gui.view.base;

import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

@UIScope
@SpringView
public abstract class BaseView<T> extends MVerticalLayout implements View {

    protected BaseController<T, ?> controller;

    protected MGrid<T> grid;
    private MButton bNew;
    private MButton bEdit;
    private ConfirmButton bDelete;

    protected HorizontalLayout buttonLayout;

    public BaseView(BaseController<T, ?> controller, String title) {
        this.controller = controller;

        Label lTitle = new Label(String.format("<h2>%s</h2>", title), ContentMode.HTML);

        initializeGrid();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener((SelectionListener<T>) event -> controller.updateButtons());

        bNew = new MButton(VaadinIcons.PLUS);
        bNew.addClickListener((Button.ClickListener) event -> controller.buttonNewClicked());
        bNew.setDescription(getAddToolTip());
        bEdit = new MButton(VaadinIcons.PENCIL);
        bEdit.addClickListener((Button.ClickListener) event -> controller.buttonEditClicked());
        bEdit.setDescription(getEditToolTip());
        bDelete = new ConfirmButton(
                VaadinIcons.TRASH,
                getDeleteConfirmationText(),
                () -> controller.buttonDeleteClicked());
        bDelete.setOkCaption("Löschen");
        bDelete.setCancelCaption("Abbrechen");
        bDelete.setConfirmWindowCaption(getWindowCaption());
        bDelete.setDescription(getDeleteToolTip());

        buttonLayout = new HorizontalLayout();
        buttonLayout.addComponent(bNew);
        buttonLayout.addComponent(bEdit);
        buttonLayout.addComponent(bDelete);

        addComponent(lTitle);
        addComponent(buttonLayout);
        addComponent(grid);
    }

    public void setGridItems(List<T> items) {
        grid.setRows(items);
    }

    public T getSelectedItem() {
        return grid.getSelectedItems().iterator().next();
    }

    public boolean isSelected() {
        return grid.getSelectedItems().size() > 0;
    }

    public void setButtonEditEnabled(boolean b) {
        bEdit.setEnabled(b);
    }

    public void setButtonDeleteEnabled(boolean b) {
        bDelete.setEnabled(b);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        controller.updateGridItems();
    }

    protected abstract String getAddToolTip();

    protected abstract String getEditToolTip();

    protected abstract String getDeleteToolTip();

    protected abstract String getDeleteConfirmationText();

    protected abstract String getWindowCaption();

    protected abstract void initializeGrid();

}
