package de.naju.ahlen.gui.view.person;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import de.naju.ahlen.gui.view.base.BaseView;
import de.naju.ahlen.persistence.model.Person;
import org.vaadin.viritin.grid.MGrid;

@UIScope
@SpringView
public class PersonView extends BaseView<Person> {

    public PersonView(PersonController controller) {
        super(controller, "Mitglieder");
    }

    @Override
    protected String getDeleteConfirmationText() {
        return "Sind Sie sicher, dass Sie die Person löschen wollen?";
    }

    @Override
    protected String getWindowCaption() {
        return "Person löschen";
    }

    @Override
    protected void initializeGrid() {
        grid = new MGrid<>(Person.class)
                .withProperties("firstName", "lastName", "street", "streetNumber", "zipCode", "city", "email")
                .withColumnHeaders("Vorname", "Nachname", "Straße", "Hausnummer", "Postleitzahl", "Stadt", "eMail")
                .withFullWidth()
                .withFullHeight();
    }
}
