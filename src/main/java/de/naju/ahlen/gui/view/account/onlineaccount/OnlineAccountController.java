package de.naju.ahlen.gui.view.account.onlineaccount;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import de.naju.ahlen.gui.view.account.AccountController;
import de.naju.ahlen.gui.view.account.cashaccount.CashAccountForm;
import de.naju.ahlen.gui.view.account.cashaccount.CashAccountView;
import de.naju.ahlen.gui.view.base.BaseController;
import de.naju.ahlen.persistence.model.*;
import javafx.util.converter.BigDecimalStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Controller
public class OnlineAccountController extends BaseController<OnlineAccount, Long> {

    @Autowired
    private AccountController controller;
    
    private Dao<Transaction, Long> transactionDao;

    public OnlineAccountController() {
        view = new OnlineAccountView(this);
        form = new OnlineAccountForm(this);
    }

    @Autowired
    @Override
    public void setDao(Dao<OnlineAccount, Long> dao) {
        this.dao = dao;
    }
    
    @Autowired
    public void setTransactionDao(Dao<Transaction, Long> transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    protected void initData() {
        updateGridItems();
    }

    public Collection<? extends AbstractAccount> getAccounts() throws SQLException {
        return dao.queryForAll();
    }

    public void editEntity(OnlineAccount entity) {
        form.setEntity(entity);
        form.openInModalPopup();
    }

    public void newEntity() {
        form.setEntity(new OnlineAccount());
        form.openInModalPopup();
    }

    @Override
    public void formSaved(OnlineAccount entity) {
        try {
            dao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateGridItems();
        controller.updateGridItems();
        form.closePopup();
    }

    @Override
    public void buttonDeleteClicked() {
        System.out.println("Button delete clicked");

        try {
            dao.delete(view.getSelectedItem());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateGridItems();
        controller.updateGridItems();
    }

    public void deleteEntity(OnlineAccount entity) {
        try {
            dao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateGridItems();
        controller.updateGridItems();
    }

    public BigDecimal getCurrentAmount(OnlineAccount account) {
        String sql = String.format("select amount from transactions where accounttype = %d and account_id = %d",
                3, account.getId());
        BigDecimal amount = account.getStartAmount();
        try {
            List<String[]> result = transactionDao.queryRaw(sql).getResults();
            for (String[] s : result) {
                BigDecimal value = new BigDecimal(s[0]);
                amount = amount.add(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }
}
