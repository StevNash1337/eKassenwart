package de.naju.ahlen.gui.view.account.onlineaccount;

import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.validator.StringLengthValidator;
import de.naju.ahlen.gui.view.account.cashaccount.CashAccountController;
import de.naju.ahlen.gui.view.base.BaseForm;
import de.naju.ahlen.persistence.model.CashAccount;
import de.naju.ahlen.persistence.model.OnlineAccount;
import org.vaadin.viritin.fields.MTextField;

import java.math.BigDecimal;

public class OnlineAccountForm extends BaseForm<OnlineAccount> {

    private MTextField name = new MTextField("Name");
    private MTextField startAmount = new MTextField("Startbetrag");
    private MTextField email = new MTextField("eMail");

    public OnlineAccountForm(OnlineAccountController controller) {
        super(controller);

        binder.forField(name)
                .withNullRepresentation("")
                .withValidator(new StringLengthValidator("Name darf nicht leer sein", 1, Integer.MAX_VALUE))
                .bind(OnlineAccount::getName, OnlineAccount::setName);

        binder.forField(startAmount)
                .withNullRepresentation("")
                .withConverter(new StringToBigDecimalConverter(BigDecimal.ZERO, "Falsches Format"))
                .bind(OnlineAccount::getStartAmount, OnlineAccount::setStartAmount);

        binder.forField(email)
                .withValidator(new StringLengthValidator("eMail darf nicht leer sein", 1, Integer.MAX_VALUE))
                .bind(OnlineAccount::getEmail, OnlineAccount::setEmail);
    }
}
