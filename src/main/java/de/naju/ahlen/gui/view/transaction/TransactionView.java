package de.naju.ahlen.gui.view.transaction;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import de.naju.ahlen.gui.view.base.BaseView;
import de.naju.ahlen.persistence.model.Transaction;
import de.naju.ahlen.util.Util;
import org.vaadin.viritin.grid.MGrid;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@UIScope
@SpringView
public class TransactionView extends BaseView<Transaction> {

    public TransactionView(TransactionController controller) {
        super(controller, "Buchungen");
    }

    @Override
    protected String getDeleteConfirmationText() {
        return "Sind Sie sicher, dass Sie die Buchung löschen wollen?";
    }

    @Override
    protected String getWindowCaption() {
        return "Transaktion löschen";
    }

    @Override
    protected void initializeGrid() {
        grid = new MGrid<>(Transaction.class);
        grid.removeAllColumns();
        grid.addColumn(item -> Util.dateFormat().format(item.getDate()));
        grid.addColumn(item -> item.getAccount().getName());
        grid.addColumn(item -> item.getCategory().getValue());
        grid.addColumn(item -> item.getText());
        grid.addColumn(item -> Util.decimalFormat().format(item.getAmount()));
        grid.withColumnHeaders("Datum", "Konto", "Kategorie", "Text", "Betrag");
        grid.setSizeFull();
    }
}
