package de.naju.ahlen.gui.view.bankaccount;

import de.naju.ahlen.persistence.model.BankAccount;
import de.naju.ahlen.persistence.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Steffen on 18.05.2017.
 */
@Component
public class BankAccountController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private BankAccountView bankAccountView;
    private BankAccountForm bankAccountForm;

    public BankAccountController() {
        this.bankAccountView = new BankAccountView(this);
        this.bankAccountForm = new BankAccountForm(this);
    }

    public BankAccountRepository getBankAccountRepository() {
        return bankAccountRepository;
    }

    public BankAccountView getBankAccountView() {
        return bankAccountView;
    }

    public void updateBankAccounts() {
        bankAccountView.setBankAccountGridItems(bankAccountRepository.findAll());
        updateButtons();
    }

    /**
     * Sollte später entfernt werden. Nur zu Debugzwecken
     */
    public void updateButtons() {
        if (!isSelected()) {
            bankAccountView.setButtonEditEnabled(false);
            bankAccountView.setButtonDeleteEnabled(false);
        } else {
            bankAccountView.setButtonEditEnabled(true);
            bankAccountView.setButtonDeleteEnabled(true);
        }
    }

    public void buttonNewClicked() {
        bankAccountForm.setEntity(new BankAccount());
        bankAccountForm.openInModalPopup();
    }

    public void buttonEditClicked() {
        Set<BankAccount> selectedBankAccounts = bankAccountView.getSelectedBankAccounts();
        bankAccountForm.setEntity(selectedBankAccounts.iterator().next());
        bankAccountForm.openInModalPopup();
    }

    public void buttonDeleteClicked() {
        Set<BankAccount> selectedBankAccounts = bankAccountView.getSelectedBankAccounts();
        bankAccountRepository.delete(selectedBankAccounts);
        updateBankAccounts();
    }

    public void bankAccountFormSaved(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
        updateBankAccounts();
        bankAccountForm.closePopup();
    }

    public void bankAccountFormCanceled() {
        updateBankAccounts();
        bankAccountForm.closePopup();
    }

    private boolean isSelected() {
        return bankAccountView.getSelectedBankAccounts().size() > 0;
    }
}
