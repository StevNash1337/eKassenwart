package de.naju.ahlen.persistence.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import de.naju.ahlen.persistence.model.*;
import de.naju.ahlen.util.orm.AbstractRoutingConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import javax.print.Doc;
import java.sql.SQLException;

/**
 * Class that provides beans for all daos except for the dao for the projects.
 */
@Controller
public class DaoController {

    @Autowired
    AbstractRoutingConnectionSource connectionSource;

    @Bean
    public Dao<Category, Long> categoryDao() throws SQLException {
        return DaoManager.createDao(connectionSource, Category.class);
    }

    @Bean
    public Dao<Person, Long> personDao() throws SQLException {
        return DaoManager.createDao(connectionSource, Person.class);
    }

    @Bean
    public Dao<CashAccount, Long> cashAccountDao() throws SQLException {
        return DaoManager.createDao(connectionSource, CashAccount.class);
    }

    @Bean
    public Dao<BankAccount, Long> bankAccountDao() throws SQLException {
        return DaoManager.createDao(connectionSource, BankAccount.class);
    }

    @Bean
    public Dao<OnlineAccount, Long> onlineAccountDao() throws SQLException {
        return DaoManager.createDao(connectionSource, OnlineAccount.class);
    }

    @Bean
    public Dao<Transaction, Long> transactionsDao() throws SQLException {
        return DaoManager.createDao(connectionSource, Transaction.class);
    }

    @Bean Dao<Document, Long> documentDao() throws SQLException {
        return DaoManager.createDao(connectionSource, Document.class);
    }
}
