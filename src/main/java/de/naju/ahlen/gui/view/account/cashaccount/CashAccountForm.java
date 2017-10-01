package de.naju.ahlen.gui.view.account.cashaccount;

import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.SerializablePredicate;
import de.naju.ahlen.gui.view.base.BaseForm;
import de.naju.ahlen.persistence.model.CashAccount;
import org.vaadin.viritin.fields.MTextField;

import java.math.BigDecimal;

public class CashAccountForm extends BaseForm<CashAccount> {

    private MTextField name = new MTextField("Name");
    private MTextField startAmount = new MTextField("Startbetrag");

    public CashAccountForm(CashAccountController controller) {
        super(controller);

        SerializablePredicate<String> nameNotEmptyPredicate =
                (SerializablePredicate<String>) v -> !name.isEmpty();

        SerializablePredicate<String> startAmountNotEmptyPredicate =
                (SerializablePredicate<String>) v -> !startAmount.isEmpty();

        binder.forField(name)
                .withNullRepresentation("")
                .withValidator(nameNotEmptyPredicate, "Name darf nicht leer sein")
                .bind(CashAccount::getName, CashAccount::setName);

        binder.forField(startAmount)
                .withNullRepresentation("")
                .withValidator(startAmountNotEmptyPredicate, "Startbetrag darf nicht leer sein")
                .withConverter(new StringToBigDecimalConverter(BigDecimal.ZERO, "Falsches Format"))
                .bind(CashAccount::getStartAmount, CashAccount::setStartAmount);
    }
}
