package de.naju.ahlen.gui.view.person;

import de.naju.ahlen.persistence.model.Person;
import de.naju.ahlen.persistence.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by lucas on 17.05.17.
 */

@Component
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    private PersonView personView;
    private PersonForm personForm;

    public PersonController() {
        this.personView = new PersonView(this);
        this.personForm = new PersonForm(this);
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public PersonView getPersonView() {
        return personView;
    }

    public void updatePersons() {
        personView.setPersonGridItems(personRepository.findAll());
        updateButtons();
    }

    public void updateButtons() {
        if (!isSelected()) {
            personView.setButtonEditEnabled(false);
            personView.setButtonDeleteEnabled(false);
        } else {
            personView.setButtonEditEnabled(true);
            personView.setButtonDeleteEnabled(true);
        }
    }

    public void buttonNewClicked() {
        personForm.setEntity(new Person());
        personForm.openInModalPopup();
    }

    public void buttonEditClicked() {
        Set<Person> selectedPersons = personView.getSelectedPersons();
        personForm.setEntity(selectedPersons.iterator().next());
        personForm.openInModalPopup();
    }

    public void buttonDeleteClicked() {
        Set<Person> selectedPersons = personView.getSelectedPersons();
        personRepository.delete(selectedPersons);
        updatePersons();
    }

    public void personFormSaved(Person person) {
        personRepository.save(person);
        updatePersons();
        personForm.closePopup();
    }

    public void personFormCanceled() {
        updatePersons();
        personForm.closePopup();
    }

    private boolean isSelected() {
        return personView.getSelectedPersons().size() > 0;
    }
}
