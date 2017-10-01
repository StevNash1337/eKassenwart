package de.naju.ahlen.gui.view.person;

import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.SerializablePredicate;
import de.naju.ahlen.gui.view.base.BaseForm;
import de.naju.ahlen.persistence.model.Person;
import org.vaadin.viritin.fields.MTextField;

public class PersonForm extends BaseForm<Person> {

    private MTextField firstName = new MTextField("Vorname");
    private MTextField lastName = new MTextField("Nachname");
    private MTextField street = new MTextField("Straße");
    private MTextField streetNumber = new MTextField("Hausnummer");
    private MTextField zipCode = new MTextField("Postleitzahl");
    private MTextField city = new MTextField("Stadt");
    private MTextField email = new MTextField("eMail");

        public PersonForm(PersonController controller) {
        super(controller);

        SerializablePredicate<String> firstNameNotEmptyPredicate =
                (SerializablePredicate<String>) v -> !firstName.isEmpty();

        SerializablePredicate<String> lastNameNotEmptyPredicate =
                (SerializablePredicate<String>) v -> !lastName.isEmpty();

        binder.forField(firstName)
                .withValidator(firstNameNotEmptyPredicate, "Vorname darf nicht leer sein")
                .bind(Person::getFirstName, Person::setFirstName);
        binder.forField(lastName)
                .withValidator(lastNameNotEmptyPredicate, "Nachname darf nicht leer sein")
                .bind(Person::getLastName, Person::setLastName);
        binder.forField(zipCode)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Falsche Eingabe"))
                .bind(Person::getZipCode, Person::setZipCode);

        bind();
    }
}
