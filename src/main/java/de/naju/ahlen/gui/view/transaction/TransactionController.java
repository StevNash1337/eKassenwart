package de.naju.ahlen.gui.view.transaction;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import de.naju.ahlen.gui.view.base.BaseController;
import de.naju.ahlen.persistence.model.*;
import de.naju.ahlen.persistence.model.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.*;

@Controller
public class TransactionController extends BaseController<Transaction, Long> {

    private Dao<CashAccount, Long> cashAccountDao;
    private Dao<BankAccount, Long> bankAccountDao;
    private Dao<OnlineAccount, Long> onlineAccountDao;
    private Dao<Category, Long> categorieDao;

    public TransactionController() {
        view = new TransactionView(this);
        //form = new TransactionForm(this);
    }

    @Override
    public void updateGridItems() {
        try {
            List<Transaction> transactions = new LinkedList<>();

            // select all transaction with cash account
            List<Transaction> cashAccountTransactions =
                    dao.queryBuilder().selectColumns("id", "date", "accounttype", "category_id", "text", "amount")
                            .where().eq("accounttype", 1).query();

            GenericRawResults<String[]> cashAccountIds =
                    dao.queryRaw("select id, account_id from `transactions` where `accounttype` = 1");

            Map<Long, AbstractAccount> cashAccountMap = new HashMap<>();
            for (String[] row : cashAccountIds.getResults()) {
                Long transactionId = Long.parseLong(row[0]);
                Long accountId = Long.parseLong(row[1]);
                AbstractAccount account = cashAccountDao.queryForId(accountId);

                cashAccountMap.put(transactionId, account);
            }

            for (Transaction t : cashAccountTransactions) {
                t.setAccount(cashAccountMap.get(t.getId()));
            }

            // select all transaction with bank account
            List<Transaction> bankAccountTransactions =
                    dao.queryBuilder().selectColumns("id", "date", "accounttype", "category_id", "text", "amount")
                            .where().eq("accounttype", 2).query();

            GenericRawResults<String[]> bankAccountIds =
                    dao.queryRaw("select id, account_id from `transactions` where `accounttype` = 2");

            Map<Long, AbstractAccount> bankAccountMap = new HashMap<>();
            for (String[] row : bankAccountIds.getResults()) {
                Long transactionId = Long.parseLong(row[0]);
                Long accountId = Long.parseLong(row[1]);
                AbstractAccount account = bankAccountDao.queryForId(accountId);

                bankAccountMap.put(transactionId, account);
            }

            for (Transaction t : bankAccountTransactions) {
                t.setAccount(bankAccountMap.get(t.getId()));
            }

            // select all transaction with online account
            List<Transaction> onlineAccountTransactions =
                    dao.queryBuilder().selectColumns("id", "date", "accounttype", "category_id", "text", "amount")
                            .where().eq("accounttype", 3).query();

            GenericRawResults<String[]> onlineAccountIds =
                    dao.queryRaw("select id, account_id from `transactions` where `accounttype` = 3");

            Map<Long, AbstractAccount> onlineAccountMap = new HashMap<>();
            for (String[] row : onlineAccountIds.getResults()) {
                Long transactionId = Long.parseLong(row[0]);
                Long accountId = Long.parseLong(row[1]);
                AbstractAccount account = onlineAccountDao.queryForId(accountId);

                onlineAccountMap.put(transactionId, account);
            }

            for (Transaction t : onlineAccountTransactions) {
                t.setAccount(onlineAccountMap.get(t.getId()));
            }

            transactions.addAll(cashAccountTransactions);
            transactions.addAll(bankAccountTransactions);
            transactions.addAll(onlineAccountTransactions);

            view.setGridItems(transactions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateButtons();
    }

    @Override
    public void formSaved(Transaction entity) {
        try {
            if (entity.getAccount() instanceof CashAccount) {
                entity.setAccountType(1);
            } else if (entity.getAccount() instanceof BankAccount) {
                entity.setAccountType(2);
            } else if (entity.getAccount() instanceof OnlineAccount) {
                entity.setAccountType(3);
            }
            dao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateGridItems();
        form.closePopup();
    }

    @PostConstruct
    private void initializeController() {
        form = new TransactionForm(this);
    }

    @Override
    @Autowired
    public void setDao(Dao<Transaction, Long> personDao) {
        this.dao = personDao;
    }

    @Autowired
    public void setCashAccountDao(Dao<CashAccount, Long> cashAccountDao) {
        this.cashAccountDao = cashAccountDao;
    }

    @Autowired
    public void setBankAccountDao(Dao<BankAccount, Long> bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    @Autowired
    public void setOnlineAccountDao(Dao<OnlineAccount, Long> onlineAccountDao) {
        this.onlineAccountDao = onlineAccountDao;
    }

    @Autowired
    public void setCategorieDao(Dao<Category, Long> categorieDao) {
        this.categorieDao = categorieDao;
    }

    @Override
    @PostConstruct
    protected void initData() {
        updateGridItems();
    }

    public List<AbstractAccount> getAccounts() {
        List<AbstractAccount> accounts = new LinkedList<>();

        try {
            accounts.addAll(cashAccountDao.queryForAll());
            accounts.addAll(bankAccountDao.queryForAll());
            accounts.addAll(onlineAccountDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        accounts.sort(Comparator.comparing(AbstractAccount::getName));

        return accounts;
    }


    public List<Category> getCategories(TransactionType type) {
        try {
            return categorieDao.queryBuilder().where().eq("type", type).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LinkedList<>();
    }
}
