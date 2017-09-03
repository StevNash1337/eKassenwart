package de.naju.ahlen.gui.view.transaction;

import de.naju.ahlen.gui.view.person.PersonView;
import de.naju.ahlen.persistence.model.Person;
import de.naju.ahlen.persistence.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by lucas on 17.05.17.
 */

@Component
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    private TransactionView transactionView;
    private TransactionForm personForm;

    public TransactionController() {
//        this.transactionView = new PersonView(this);
        this.personForm = new TransactionForm(this);
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public TransactionView getTransactionView() {
        return transactionView;
    }

    public void updatePersons() {
//        transactionView.setPersonGridItems(transactionRepository.findAll());
        updateButtons();
    }

    public void updateButtons() {
//        if (!isSelected()) {
//            transactionView.setButtonEditEnabled(false);
//            transactionView.setButtonDeleteEnabled(false);
//        } else {
//            transactionView.setButtonEditEnabled(true);
//            transactionView.setButtonDeleteEnabled(true);
//        }
    }

    public void buttonNewClicked() {
        personForm.setEntity(new Person());
        personForm.openInModalPopup();
    }

    public void buttonEditClicked() {
//        Set<Person> selectedPersons = transactionView.getSelectedPersons();
//        personForm.setEntity(selectedPersons.iterator().next());
//        personForm.openInModalPopup();
    }

    public void buttonDeleteClicked() {
//        Set<Person> selectedPersons = transactionView.getSelectedPersons();
//        transactionRepository.delete(selectedPersons);
        updatePersons();
    }

    public void personFormSaved(Person person) {
//        transactionRepository.save(person);
        updatePersons();
        personForm.closePopup();
    }

    public void personFormCanceled() {
        updatePersons();
        personForm.closePopup();
    }

//    private boolean isSelected() {
//        return transactionView.getSelectedPersons().size() > 0;
//    }
}
