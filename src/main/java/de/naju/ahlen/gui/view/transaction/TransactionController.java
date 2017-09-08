package de.naju.ahlen.gui.view.transaction;

import de.naju.ahlen.persistence.model.Transaction;
import de.naju.ahlen.persistence.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lucas on 17.05.17.
 */

@Component
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    private TransactionView transactionView;
    private TransactionForm transactionForm;

    public TransactionController() {
        this.transactionView = new TransactionView(this);
        this.transactionForm = new TransactionForm(this);
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public TransactionView getTransactionView() {
        return transactionView;
    }

    public void updateTransactions() {
//        transactionView.settransactionGridItems(transactionRepository.findAll());
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
        transactionForm.setEntity(new Transaction());
        transactionForm.openInModalPopup();
    }

    public void buttonEditClicked() {
//        Set<transaction> selectedtransactions = transactionView.getSelectedtransactions();
//        transactionForm.setEntity(selectedtransactions.iterator().next());
//        transactionForm.openInModalPopup();
    }

    public void buttonDeleteClicked() {
//        Set<transaction> selectedtransactions = transactionView.getSelectedtransactions();
//        transactionRepository.delete(selectedtransactions);
        updateTransactions();
    }

    public void transactionFormSaved(Transaction transaction) {
//        transactionRepository.save(transaction);
        updateTransactions();
        transactionForm.closePopup();
    }

    public void transactionFormCanceled() {
        updateTransactions();
        transactionForm.closePopup();
    }

//    private boolean isSelected() {
//        return transactionView.getSelectedtransactions().size() > 0;
//    }
}
