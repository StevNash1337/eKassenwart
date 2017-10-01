package de.naju.ahlen.gui.view.account;

import de.naju.ahlen.gui.view.account.onlineaccount.OnlineAccountController;
import de.naju.ahlen.gui.view.base.BaseView;
import de.naju.ahlen.persistence.model.AbstractAccount;
import de.naju.ahlen.util.Util;
import org.vaadin.viritin.grid.MGrid;

public class AccountView extends BaseView<AbstractAccount> {

    public AccountView(AccountController controller) {
        super(controller, "Konten");
    }

    @Override
    protected String getAddToolTip() {
        return "Neues Konto erstellen";
    }

    @Override
    protected String getEditToolTip() {
        return "Konto bearbeiten";
    }

    @Override
    protected String getDeleteToolTip() {
        return "Konto löschen";
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
        grid = new MGrid<>(AbstractAccount.class);
        grid.removeAllColumns();
        grid.addColumn(item -> item.getName());
        grid.addColumn(item -> Util.decimalFormat().format(((AccountController)controller).getCurrentAmount(item)));
        grid.addColumn(item -> Util.decimalFormat().format(item.getStartAmount()));
        grid.withColumnHeaders("Name", "Betrag", "Startbetrag");
        grid.setSizeFull();
    }
}
