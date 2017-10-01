package de.naju.ahlen.gui.view.account.bankaccount;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import de.naju.ahlen.gui.view.base.BaseView;
import de.naju.ahlen.persistence.model.BankAccount;
import de.naju.ahlen.util.Util;
import org.vaadin.viritin.grid.MGrid;

@UIScope
@SpringView
public class BankAccountView extends BaseView<BankAccount> {

    public BankAccountView(BankAccountController controller) {
        super(controller, "Bankkonten");
    }

    @Override
    protected String getDeleteConfirmationText() {
        return "Sind Sie sicher, dass Sie das Konto löschen wollen?";
    }

    @Override
    protected String getWindowCaption() {
        return "Konto löschen";
    }

    @Override
    protected void initializeGrid() {
        grid = new MGrid<>(BankAccount.class);
        grid.removeAllColumns();
        grid.addColumn(item -> item.getName());
        grid.addColumn(item -> Util.decimalFormat().format(((BankAccountController)controller).getCurrentAmount(item)));
        grid.addColumn(item -> Util.decimalFormat().format(item.getStartAmount()));
        grid.addColumn(item -> item.getBankNumber());
        grid.addColumn(item -> item.getAccountNumber());
        grid.addColumn(item -> item.getIban());
        grid.withColumnHeaders("Name", "Betrag", "Startbetrag", "Bankleitzahl", "Kontonummer", "IBAN");
        grid.setSizeFull();
    }
}
