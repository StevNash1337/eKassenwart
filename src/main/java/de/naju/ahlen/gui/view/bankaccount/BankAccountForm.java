package de.naju.ahlen.gui.view.bankaccount;

import com.vaadin.ui.Component;
import de.naju.ahlen.persistence.model.BankAccount;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by Steffen on 18.05.2017.
 */
public class BankAccountForm extends AbstractForm<BankAccount>{

    private BankAccountController bankAccountController;

    private MTextField name;
    private MTextField startAmount;
    private MTextField actualAmount;
    private MTextField accountNumber;
    private MTextField bankNumber;

    public BankAccountForm(BankAccountController bankAccountController) {
        super(BankAccount.class);
        this.bankAccountController = bankAccountController;

        name = new MTextField("Bezeichnung");
        startAmount = new MTextField("Startbetrag");
        actualAmount = new MTextField("Aktueller Betrag");
        accountNumber = new MTextField("Kontonummer");
        bankNumber = new MTextField("Bankleitzahl");

        setSavedHandler(new AbstractForm.SavedHandler<BankAccount>() {
            @Override
            public void onSave(BankAccount bankAccount) {
                bankAccountController.bankAccountFormSaved(bankAccount);
            }
        });

        setResetHandler(new AbstractForm.ResetHandler<BankAccount>() {
            @Override
            public void onReset(BankAccount bankAccount) {
                bankAccountController.bankAccountFormCanceled();
            }
        });

        setSizeUndefined();
    }

    public void setResetHandler(ResetHandler<BankAccount> resetHandler) {
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new MFormLayout(
                        name,
                        startAmount,
                        actualAmount,
                        accountNumber,
                        bankNumber
                ).withWidth(""), getToolbar()
        ).withWidth("");
    }

}
