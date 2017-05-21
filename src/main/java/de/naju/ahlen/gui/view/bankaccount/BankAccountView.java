package de.naju.ahlen.gui.view.bankaccount;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.naju.ahlen.persistence.model.BankAccount;
import org.vaadin.viritin.grid.MGrid;

import java.util.List;
import java.util.Set;

/**
 * Created by Steffen on 18.05.2017.
 */
public class BankAccountView  extends VerticalLayout implements View {

    private BankAccountController bankAccountController;

    private MGrid<BankAccount> bankAccountMGrid;
    private TextField tSearch;
    private Button bNew;
    private Button bEdit;
    private Button bDelete;


    public BankAccountView(BankAccountController bankAccountController) {
        this.bankAccountController = bankAccountController;

        bankAccountMGrid = new MGrid<>(BankAccount.class)
                .withProperties("name", "startAmount", "actualAmount", "accountNumber", "bankNumber")
                .withColumnHeaders("Bezeichnung", "Startbetrag", "Aktueller Betrag", "Kontonummer", "Bankleitzahl")
                .withFullWidth();

        bankAccountMGrid.addSelectionListener(new SelectionListener<BankAccount>() {
            @Override
            public void selectionChange(SelectionEvent<BankAccount> selectionEvent) {
                bankAccountController.updateButtons();
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
                bankAccountController.buttonNewClicked();
            }
        });

        bEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                bankAccountController.buttonEditClicked();
            }
        });

        bDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                bankAccountController.buttonDeleteClicked();
            }
        });


        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addComponent(tSearch);
        buttonLayout.addComponent(bNew);
        buttonLayout.addComponent(bEdit);
        buttonLayout.addComponent(bDelete);

        addComponent(buttonLayout);
        addComponent(bankAccountMGrid);
    }

    public void setBankAccountGridItems(List<BankAccount> items) {
        bankAccountMGrid.setItems(items);
    }

    public String getSearchText() {
        return tSearch.getValue();
    }

    public Set<BankAccount> getSelectedBankAccounts() {
        return bankAccountMGrid.getSelectedItems();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        bankAccountController.updateBankAccounts();
    }

    public void setButtonEditEnabled(boolean b) {
        bEdit.setEnabled(b);
    }

    public void setButtonDeleteEnabled(boolean b) {
        bDelete.setEnabled(b);
    }
}