package de.naju.ahlen.gui.view.account.cashaccount;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import de.naju.ahlen.gui.view.base.BaseView;
import de.naju.ahlen.persistence.model.CashAccount;
import de.naju.ahlen.util.Util;
import org.vaadin.viritin.grid.MGrid;

@UIScope
@SpringView
public class CashAccountView extends BaseView<CashAccount> {

    public CashAccountView(CashAccountController controller) {
        super(controller, "Barkonten");
    }

    @Override
    protected String getAddToolTip() {
        return "Neues Barkonto erstellen";
    }

    @Override
    protected String getEditToolTip() {
        return "Barkonto bearbeiten";
    }

    @Override
    protected String getDeleteToolTip() {
        return "Barkonto löschen";
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
        grid = new MGrid<>(CashAccount.class);
        grid.removeAllColumns();
        grid.addColumn(item -> item.getName());
        grid.addColumn(item -> Util.decimalFormat().format(((CashAccountController)controller).getCurrentAmount(item)));
        grid.addColumn(item -> Util.decimalFormat().format(item.getStartAmount()));
        grid.withColumnHeaders("Name", "Betrag", "Startbetrag");
        grid.setSizeFull();
    }
}
