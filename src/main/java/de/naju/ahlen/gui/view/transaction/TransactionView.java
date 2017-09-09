package de.naju.ahlen.gui.view.transaction;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import de.naju.ahlen.persistence.model.Transaction;
import org.vaadin.viritin.grid.MGrid;

import java.util.*;

/**
 * Created by Steffen on 25.04.2017.
 */
@SpringView
public class TransactionView extends VerticalLayout implements View {

    private TransactionController transactionController;

    private MGrid<Transaction> transactionGrid;
    private TextField tSearch;
    private Button bNew;
    private Button bEdit;
    private Button bDelete;


    public TransactionView(TransactionController transactionController) {
        this.transactionController = transactionController;

        transactionGrid = new MGrid<>(Transaction.class)
                .withProperties("date", "category", "description", "amount", "person", "comment", "account", "payed")
                .withColumnHeaders("Datum", "Kategorie", "Beschreibung", "Betrag", "Person", "Kommentar", "Konto", "Bezahlt?")
                .withFullWidth();

        transactionGrid.addSelectionListener(new SelectionListener<Transaction>() {
            @Override
            public void selectionChange(SelectionEvent<Transaction> selectionEvent) {
                transactionController.updateButtons();
            }
        });

        tSearch = new TextField();
        //tSearch.setIcon(VaadinIcons.SEARCH);

        bNew = new Button(VaadinIcons.PLUS);
        bEdit = new Button(VaadinIcons.PENCIL);
        bDelete = new Button(VaadinIcons.TRASH);

        bNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                transactionController.buttonNewClicked();
            }
        });

        bEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                transactionController.buttonEditClicked();
            }
        });

        bDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                transactionController.buttonDeleteClicked();
            }
        });


        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addComponent(tSearch);
        buttonLayout.addComponent(bNew);
        buttonLayout.addComponent(bEdit);
        buttonLayout.addComponent(bDelete);

        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.addComponent(transactionGrid);

        setWidth("100%");
        setHeight("100%");
//        setSizeFull();

//        buttonLayout.setHeight(null);
//        buttonLayout.setSizeUndefined();
//        buttonLayout.setWidth("100%");

        gridLayout.setWidth("100%");
        gridLayout.setHeight("100%");
        gridLayout.setSizeFull();

        addComponents(buttonLayout, gridLayout);
        setExpandRatio(buttonLayout, 0.0f);
        setExpandRatio(gridLayout, 1.0f);
    }

    public void setTransactionGridItems(List<Transaction> items) {
        transactionGrid.setItems(items);
    }

    public String getSearchText() {
        return tSearch.getValue();
    }

    public Set<Transaction> getSelectedTransactions() {
        return transactionGrid.getSelectedItems();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        transactionController.updateTransactions();
    }

    public void setButtonEditEnabled(boolean b) {
        bEdit.setEnabled(b);
    }

    public void setButtonDeleteEnabled(boolean b) {
        bDelete.setEnabled(b);
    }
}
