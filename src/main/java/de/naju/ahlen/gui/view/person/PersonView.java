package de.naju.ahlen.gui.view.person;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.naju.ahlen.persistence.model.Person;
import org.vaadin.viritin.grid.MGrid;

import java.util.List;
import java.util.Set;

/**
 * Created by lucas on 17.05.17.
 */
public class PersonView extends VerticalLayout implements View {

    private PersonController personController;

    private MGrid<Person> personGrid;
    private TextField tSearch;
    private Button bNew;
    private Button bEdit;
    private Button bDelete;


    public PersonView(PersonController personController) {
        this.personController = personController;

        personGrid = new MGrid<>(Person.class)
                .withProperties("firstName", "lastName", "street", "streetNumber", "zipCode", "city")
                .withColumnHeaders("Vorname", "Nachname", "Straße", "Hausnummer", "Postleitzahl", "Ort")
                .withFullWidth();

        personGrid.addSelectionListener(new SelectionListener<Person>() {
            @Override
            public void selectionChange(SelectionEvent<Person> selectionEvent) {
                personController.updateButtons();
            }
        });

        tSearch = new TextField();
        //tSearch.setIcon(VaadinIcons.SEARCH);

        bNew = new Button(VaadinIcons.PLUS);
        bEdit = new Button(VaadinIcons.PENCIL);
        bDelete = new Button(VaadinIcons.TRASH);

        bNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                personController.buttonNewClicked();
            }
        });

        bEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                personController.buttonEditClicked();
            }
        });

        bDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                personController.buttonDeleteClicked();
            }
        });


        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addComponent(tSearch);
        buttonLayout.addComponent(bNew);
        buttonLayout.addComponent(bEdit);
        buttonLayout.addComponent(bDelete);

        addComponent(buttonLayout);
        addComponent(personGrid);
    }

    public void setPersonGridItems(List<Person> items) {
        personGrid.setItems(items);
    }

    public String getSearchText() {
        return tSearch.getValue();
    }

    public Set<Person> getSelectedPersons() {
        return personGrid.getSelectedItems();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        personController.updatePersons();
    }

    public void setButtonEditEnabled(boolean b) {
        bEdit.setEnabled(b);
    }

    public void setButtonDeleteEnabled(boolean b) {
        bDelete.setEnabled(b);
    }
}
