package de.naju.ahlen.gui.view.account.onlineaccount;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import de.naju.ahlen.gui.view.account.cashaccount.CashAccountController;
import de.naju.ahlen.gui.view.base.BaseView;
import de.naju.ahlen.persistence.model.CashAccount;
import de.naju.ahlen.persistence.model.OnlineAccount;
import de.naju.ahlen.util.Util;
import org.vaadin.viritin.grid.MGrid;

@UIScope
@SpringView
public class OnlineAccountView extends BaseView<OnlineAccount> {

    public OnlineAccountView(OnlineAccountController controller) {
        super(controller, "Onlinekonten");
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
        grid = new MGrid<>(OnlineAccount.class);
        grid.removeAllColumns();
        grid.addColumn(item -> item.getName());
        grid.addColumn(item -> Util.decimalFormat().format(((OnlineAccountController)controller).getCurrentAmount(item)));
        grid.addColumn(item -> Util.decimalFormat().format(item.getStartAmount()));
        grid.addColumn(item -> item.getEmail());
        grid.withColumnHeaders("Name", "Betrag", "Startbetrag", "eMail");
        grid.setSizeFull();
    }
}
