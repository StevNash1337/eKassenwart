package de.naju.ahlen.gui.view.transaction;

import com.vaadin.ui.Component;
import de.naju.ahlen.persistence.model.Person;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by lucas on 17.05.17.
 */
public class TransactionForm extends AbstractForm<Person> {

    private TransactionController transactionController;

    private MTextField firstName;
    private MTextField lastName;
    private MTextField street;
    private MTextField streetNumber;
    private MTextField zipCode;
    private MTextField city;

    public TransactionForm(TransactionController transactionController) {
        super(Person.class);
        this.transactionController = transactionController;

        firstName = new MTextField("Vorname");
        lastName = new MTextField("Nachname");
        street = new MTextField("Straße");
        streetNumber = new MTextField("Hausnummer");
        zipCode = new MTextField("Postleitzahl");
        city = new MTextField("Ort");

        setSavedHandler(new SavedHandler<Person>() {
            @Override
            public void onSave(Person person) {
                transactionController.personFormSaved(person);
            }
        });

        setResetHandler(new ResetHandler<Person>() {
            @Override
            public void onReset(Person person) {
                transactionController.personFormCanceled();
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
