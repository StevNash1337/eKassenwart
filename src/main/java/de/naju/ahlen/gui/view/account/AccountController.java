package de.naju.ahlen.gui.view.account;

import com.j256.ormlite.dao.Dao;
import com.vaadin.data.HasValue;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.naju.ahlen.gui.view.account.bankaccount.BankAccountController;
import de.naju.ahlen.gui.view.account.cashaccount.CashAccountController;
import de.naju.ahlen.gui.view.account.onlineaccount.OnlineAccountController;
import de.naju.ahlen.gui.view.base.BaseController;
import de.naju.ahlen.persistence.model.*;
import de.naju.ahlen.persistence.model.enums.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AccountController extends BaseController<AbstractAccount, Long> {

    @Autowired
    private CashAccountController cashAccountController;

    @Autowired
    private BankAccountController bankAccountController;

    @Autowired
    private OnlineAccountController onlineAccountController;

    private Window selectAccountTypeWindow;
    private ComboBox<AccountType> typeComboBox;

    public AccountController() {
        view = new AccountView(this);

        List<AccountType> types = new LinkedList<>();
        types.add(AccountType.CASHACCOUNT);
        types.add(AccountType.BANKACCOUNT);
        types.add(AccountType.ONLINEACCOUNT);

        typeComboBox = new ComboBox<>();
        typeComboBox.setItems(types);
        typeComboBox.setEmptySelectionAllowed(false);
        typeComboBox.setTextInputAllowed(false);
        typeComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AccountType>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<AccountType> event) {
                if (typeComboBox.getSelectedItem().isPresent()) {
                    if (typeComboBox.getSelectedItem().get().equals(AccountType.CASHACCOUNT)) {
                        cashAccountController.newEntity();
                    } else if (typeComboBox.getSelectedItem().get().equals(AccountType.BANKACCOUNT)) {
                        bankAccountController.newEntity();
                    } else if (typeComboBox.getSelectedItem().get().equals(AccountType.ONLINEACCOUNT)) {
                        onlineAccountController.newEntity();
                    }
                }
                selectAccountTypeWindow.close();
            }
        });

        MVerticalLayout layout = new MVerticalLayout();
        layout.add(new Label("Kontotyp wählen"), typeComboBox);

        selectAccountTypeWindow = new Window("Neues Konto");
        selectAccountTypeWindow.setContent(layout);
        selectAccountTypeWindow.center();
    }

    @Override
    public void setDao(Dao<AbstractAccount, Long> dao) {

    }

    @Override
    public void buttonNewClicked() {
        typeComboBox.setSelectedItem(null);
        UI.getCurrent().addWindow(selectAccountTypeWindow);
    }

    @Override
    public void buttonEditClicked() {
        System.out.println("Button edit clicked");

        AbstractAccount entity = view.getSelectedItem();

        if (entity instanceof CashAccount) {
            cashAccountController.editEntity((CashAccount) entity);
        } else if (entity instanceof BankAccount) {
            bankAccountController.editEntity((BankAccount) entity);
        } else if (entity instanceof OnlineAccount) {
            onlineAccountController.editEntity((OnlineAccount) entity);
        }
    }

    @Override
    public void updateGridItems() {
        List<AbstractAccount> accounts = new LinkedList<>();

        try {
            accounts.addAll(cashAccountController.getAccounts());
            accounts.addAll(bankAccountController.getAccounts());
            accounts.addAll(onlineAccountController.getAccounts());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        accounts.sort(Comparator.comparing(AbstractAccount::getName));

        view.setGridItems(accounts);
        updateButtons();
    }

    @Override
    protected void initData() {
        updateGridItems();
    }

    @Override
    public void buttonDeleteClicked() {
        System.out.println("Button delete clicked");

        AbstractAccount entity = view.getSelectedItem();
        if (entity instanceof CashAccount) {
            cashAccountController.deleteEntity((CashAccount) entity);
        } else if (entity instanceof BankAccount) {
            bankAccountController.deleteEntity((BankAccount) entity);
        } else if (entity instanceof OnlineAccount) {
            onlineAccountController.deleteEntity((OnlineAccount) entity);
        }
    }

    public BigDecimal getCurrentAmount(AbstractAccount item) {
        if (item instanceof CashAccount) {
            return cashAccountController.getCurrentAmount((CashAccount) item);
        } else if (item instanceof BankAccount) {
            return bankAccountController.getCurrentAmount((BankAccount) item);
        } else if (item instanceof OnlineAccount) {
            return onlineAccountController.getCurrentAmount((OnlineAccount) item);
        }
        return BigDecimal.ZERO;
    }
}
