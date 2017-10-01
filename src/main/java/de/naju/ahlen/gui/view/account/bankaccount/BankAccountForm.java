package de.naju.ahlen.gui.view.account.bankaccount;

import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.StringLengthValidator;
import de.naju.ahlen.gui.view.account.cashaccount.CashAccountController;
import de.naju.ahlen.gui.view.base.BaseForm;
import de.naju.ahlen.persistence.model.BankAccount;
import de.naju.ahlen.persistence.model.CashAccount;
import org.vaadin.viritin.fields.MTextField;

import java.math.BigDecimal;

public class BankAccountForm extends BaseForm<BankAccount> {

    private MTextField name = new MTextField("Name");
    private MTextField startAmount = new MTextField("Startbetrag");
    private MTextField bankNumber = new MTextField("Bankleitzahl");
    private MTextField accountNumber = new MTextField("Kontonummer");
    private MTextField iban = new MTextField("IBAN");

    public BankAccountForm(BankAccountController controller) {
        super(controller);

        binder.forField(name)
                .withNullRepresentation("")
                .withValidator(new StringLengthValidator("Name darf nicht leer sein", 1, Integer.MAX_VALUE))
                .bind(BankAccount::getName, BankAccount::setName);

        binder.forField(startAmount)
                .withNullRepresentation("")
                .withConverter(new StringToBigDecimalConverter(BigDecimal.ZERO, "Falsches Format"))
                .bind(BankAccount::getStartAmount, BankAccount::setStartAmount);

        binder.forField(bankNumber)
                .withNullRepresentation("")
                .withValidator(new StringLengthValidator("Bankleitzahl darf nicht leer sein", 1, Integer.MAX_VALUE))
                .withConverter(new StringToIntegerConverter("Falsches Format"))
                .bind(BankAccount::getBankNumber, BankAccount::setBankNumber);

        binder.forField(accountNumber)
                .withNullRepresentation("")
                .withValidator(new StringLengthValidator("Kontonummer darf nicht leer sein", 1, Integer.MAX_VALUE))
                .withConverter(new StringToIntegerConverter("Falsches Format"))
                .bind(BankAccount::getAccountNumber, BankAccount::setAccountNumber);

        binder.forField(iban)
                .withValidator(new StringLengthValidator("IBAN darf nicht leer sein", 1, Integer.MAX_VALUE))
                .bind(BankAccount::getIban, BankAccount::setIban);
    }
}
