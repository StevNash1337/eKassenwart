package de.naju.ahlen.gui.view.transaction;

import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import de.naju.ahlen.gui.extensions.AbstractForm;
import de.naju.ahlen.persistence.model.AbstractAccount;
import de.naju.ahlen.persistence.model.Category;
import de.naju.ahlen.persistence.model.Person;
import de.naju.ahlen.persistence.model.Transaction;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by lucas on 17.05.17.
 */
public class TransactionForm extends AbstractForm<Transaction> {

    private TransactionController transactionController;

    private Binder<Transaction> binder = new Binder<>(Transaction.class);

    private DateField date;
    private ComboBox<Category> category;
    private MTextField description;
    private MTextField amount;
    private ComboBox<Person> person;
    private MTextField comment;
    private ComboBox<AbstractAccount> account;
    private CheckBox payed;

    public TransactionForm(TransactionController transactionController) {
        super(Transaction.class);
        this.transactionController = transactionController;

        this.setBinder(binder);

        date = new DateField("Datum");

        category = new ComboBox("Kategorie");
        category.setEmptySelectionAllowed(false);

        description = new MTextField("Beschreibung");

        amount = new MTextField("Betrag");

        person = new ComboBox("Person");
        person.setEmptySelectionAllowed(false);

        comment = new MTextField("Kommentar");

        account = new ComboBox("Konto");
        account.setEmptySelectionAllowed(false);

        payed = new CheckBox("Bezahlt?");

        binder.forField(amount)
                .withNullRepresentation("")
                .withConverter(Double::valueOf, String::valueOf, "Betrag muss Dezimalzahl sein")
                .bind(Transaction::getAmount, Transaction::setAmount);

        setSavedHandler(new SavedHandler<Transaction>() {
            @Override
            public void onSave(Transaction transaction) {
                transactionController.transactionFormSaved(transaction);
            }
        });

        setResetHandler(new ResetHandler<Transaction>() {
            @Override
            public void onReset(Transaction transaction) {
                transactionController.transactionFormCanceled();
            }
        });

        setSizeUndefined();
    }

    public void setSavedHandler(SavedHandler<Transaction> savedHandler) {
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new MFormLayout(
                        date,
                        category,
                        description,
                        amount,
                        person,
                        comment,
                        account,
                        payed
                ).withWidth(""), getToolbar()
        ).withWidth("");
    }
}
