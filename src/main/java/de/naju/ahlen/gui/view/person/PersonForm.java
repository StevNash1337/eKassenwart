package de.naju.ahlen.gui.view.person;

import com.vaadin.ui.Component;
import de.naju.ahlen.persistence.model.Person;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by lucas on 17.05.17.
 */
public class PersonForm extends AbstractForm<Person> {

    private PersonController personController;

    private MTextField firstName;
    private MTextField lastName;
    private MTextField street;
    private MTextField streetNumber;
    private MTextField zipCode;
    private MTextField city;

    public PersonForm(PersonController personController) {
        super(Person.class);
        this.personController = personController;

        firstName = new MTextField("Vorname");
        lastName = new MTextField("Nachname");
        street = new MTextField("Straße");
        streetNumber = new MTextField("Hausnummer");
        zipCode = new MTextField("Postleitzahl");
        city = new MTextField("Ort");

        setSavedHandler(new SavedHandler<Person>() {
            @Override
            public void onSave(Person person) {
                personController.personFormSaved(person);
            }
        });

        setResetHandler(new ResetHandler<Person>() {
            @Override
            public void onReset(Person person) {
                personController.personFormCanceled();
            }
        });

        setSizeUndefined();
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new MFormLayout(
                        firstName,
                        lastName,
                        street,
                        streetNumber,
                        zipCode,
                        city
                ).withWidth(""), getToolbar()
        ).withWidth("");
    }
}
