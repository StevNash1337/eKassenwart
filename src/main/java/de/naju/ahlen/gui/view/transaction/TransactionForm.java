package de.naju.ahlen.gui.view.transaction;

import com.vaadin.data.HasValue;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.ItemCaptionGenerator;
import de.naju.ahlen.gui.view.base.BaseForm;
import de.naju.ahlen.persistence.model.*;
import de.naju.ahlen.persistence.model.enums.TransactionType;
import org.vaadin.viritin.fields.MTextField;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

public class TransactionForm extends BaseForm<Transaction> {

    private ComboBox<TransactionType> type = new ComboBox<>("Typ");
    private DateField date = new DateField("Datum");
    private ComboBox<AbstractAccount> account= new ComboBox<>("Konto");
    private ComboBox<Category> category = new ComboBox<>("Kategorie");
    private MTextField text = new MTextField("Text");
    private MTextField amount = new MTextField("Betrag");

    private TransactionController controller;

    public TransactionForm(TransactionController controller) {
        super(controller);

        this.controller = controller;

        date.setTextFieldEnabled(false);

        List<TransactionType> types = new LinkedList<>();
        types.add(TransactionType.INCOME);
        types.add(TransactionType.EXPENSE);

        type.setItems(types);
        type.setEmptySelectionAllowed(false);
        type.addValueChangeListener(new HasValue.ValueChangeListener<TransactionType>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<TransactionType> event) {
                if (type.getSelectedItem().isPresent()) {
                    updateComboBoxes(type.getValue());
                }
            }
        });

        account.setItems(controller.getAccounts());
        account.setTextInputAllowed(false);
        account.setEmptySelectionAllowed(false);
        account.setItemCaptionGenerator((ItemCaptionGenerator<AbstractAccount>) item -> item.getName());
        category.setItems(controller.getCategories(TransactionType.EXPENSE));
        category.setTextInputAllowed(false);
        category.setEmptySelectionAllowed(false);
        category.setItemCaptionGenerator((ItemCaptionGenerator<Category>) item -> item.getValue());

        SerializablePredicate<Transaction> typeNotEmptyPredicate =
                (SerializablePredicate<Transaction>) v -> type.getSelectedItem().isPresent();

        SerializablePredicate<Transaction> accountNotEmptyPredicate =
                (SerializablePredicate<Transaction>) v -> account.getSelectedItem().isPresent();

        SerializablePredicate<Transaction> categoryNotEmptyPredicate =
                (SerializablePredicate<Transaction>) v -> category.getSelectedItem().isPresent();

        SerializablePredicate<LocalDate> dateNotEmptyPredicate =
                (SerializablePredicate<LocalDate>) v -> (!date.isEmpty());

        SerializablePredicate<String> textNotEmptyPredicate =
                (SerializablePredicate<String>) v -> !text.isEmpty();

        SerializablePredicate<String> amountNotEmptyPredicate =
                (SerializablePredicate<String>) v -> (!amount.isEmpty());

        binder.withValidator(typeNotEmptyPredicate, "Typ auswählen");

        binder.withValidator(accountNotEmptyPredicate, "Konto auswählen");

        binder.withValidator(categoryNotEmptyPredicate, "Konto auswählen");

        binder.forField(date)
                .withValidator(dateNotEmptyPredicate, "Datum auswählen")
                .withConverter(new LocalDateToDateConverter(ZoneId.systemDefault()))
                .bind(Transaction::getDate, Transaction::setDate);

        binder.forField(text)
                .withValidator(textNotEmptyPredicate, "Text darf nicht leer sein")
                .bind(Transaction::getText, Transaction::setText);

        binder.forField(amount)
                .withNullRepresentation("")
                .withValidator(amountNotEmptyPredicate, "Betrag darf nicht leer sein")
                .withConverter(new StringToBigDecimalConverter(BigDecimal.ZERO, "Falsche Format"))
                .bind(Transaction::getAmount, Transaction::setAmount);

        bind();
    }

    @Override
    public void setEntity(Transaction entity) {
        super.setEntity(entity);
        updateComboBoxes(TransactionType.EXPENSE);
    }

    private void updateComboBoxes(TransactionType type) {
        account.setItems(controller.getAccounts());
        category.setItems(controller.getCategories(type));
    }
}
