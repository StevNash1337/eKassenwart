package de.naju.ahlen.gui.view;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.DateRenderer;
import de.naju.ahlen.gui.view.windows.NewTransactionWindow;
import de.naju.ahlen.persistence.model.Person;
import de.naju.ahlen.persistence.model.Transaction;
import de.naju.ahlen.persistence.repositories.PersonRepository;
import de.naju.ahlen.persistence.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Steffen on 25.04.2017.
 */
@SpringView
public class TransactionView extends VerticalLayout implements View {

    private PersonRepository personRepository;
    private TransactionRepository transactionRepository;

    private Grid<Transaction> transactionGrid;
    private TextField filterTextField;
    private Button bNewTransaction;

    public TransactionView(){
        addComponent(new Label("Money Money Money!!!11^111einself"));
        transactionGrid = new Grid<>();
        transactionGrid.setWidth("100%");
        transactionGrid.setHeight("100%");
        //transactionGrid.addColumn(Transaction::getName).setCaption("Name");
        transactionGrid.addColumn(Transaction::getDate, new DateRenderer()).setCaption("Datum");
        transactionGrid.addColumn(Transaction::getPerson).setCaption("Durchgeführt/Entgegengenommen von");
        transactionGrid.addColumn(Transaction::getAmount).setCaption("Betrag");
        //transactionGrid.addColumn(Transaction::getType).setCaption("Typ");
        transactionGrid.addColumn(Transaction::getCategory).setCaption("Kategorie");
        transactionGrid.addColumn(Transaction::getComment).setCaption("Kommentar");

        Person p1 = new Person();
        p1.setLastName("Biehs");
        p1.setFirstName("Steffen");
        personRepository.save(p1);
        System.out.println(personRepository.findAll().size());


        //ListDataProvider<Transaction> dataProvider = DataProvider.ofCollection(transactions);
        //transactionGrid.setDataProvider(dataProvider);

        /**
        filterTextField = new TextField("Filter by Name");
        filterTextField.setPlaceholder("Name...");
        filterTextField.addValueChangeListener(event -> {
            dataProvider.setFilter(Transaction::getName, name -> {
                String nameLower = name == null ? ""
                        : name.toLowerCase(Locale.GERMAN);
                String filterLower = event.getValue()
                        .toLowerCase(Locale.GERMAN);
                return nameLower.contains(filterLower);
            });
        });
         */

        bNewTransaction = new Button("Neue Transaktion");
        bNewTransaction.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().addWindow(new NewTransactionWindow("Neue Transaktion"));
            }
        });

        addComponent(filterTextField);
        addComponent(transactionGrid);
        addComponent(bNewTransaction);

        setComponentAlignment(bNewTransaction, Alignment.BOTTOM_RIGHT);

//        setExpandRatio(filterTextField, 0);
//        setExpandRatio(transactionGrid, 12);
//        setExpandRatio(bNewTransaction, 0);
    }
    /**
    private Collection<Transaction> generatePeopleRandomData(){
        LinkedList<Transaction> list = new LinkedList<>();
        list.add(new Transaction("Test1",
                new Member("Steffen",
                        "B.",
                        123456,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                42,
                AmountType.Einnahme,
                TransactionCategory.Verpflegung,
                ""));
        list.add(new Transaction("Test2",
                new Member("Simoms",
                        "C.",
                        846345,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                52,
                AmountType.Einnahme,
                TransactionCategory.Spende,
                "Penispumpe"));
        list.add(new Transaction("Test3",
                new Member("Lucas",
                        "C.",
                        58245,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                62,
                AmountType.Ausgabe,
                TransactionCategory.Verpflegung,
                ""));
        list.add(new Transaction("Test4",
                new Member("David",
                        "P.",
                        9421664,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                72,
                AmountType.Einnahme,
                TransactionCategory.Verpflegung,
                "Koks"));
        list.add(new Transaction("Test5",
                new Member("Fabian",
                        "K.",
                        045124,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                82,
                AmountType.Ausgabe,
                TransactionCategory.Spende,
                "Bier"));
        list.add(new Transaction("Test1",
                new Member("Steffen",
                        "B.",
                        123456,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                42,
                AmountType.Einnahme,
                TransactionCategory.Verpflegung,
                ""));
        list.add(new Transaction("Test2",
                new Member("Simoms",
                        "C.",
                        846345,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                52,
                AmountType.Einnahme,
                TransactionCategory.Spende,
                "Penispumpe"));
        list.add(new Transaction("Test3",
                new Member("Lucas",
                        "C.",
                        58245,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                62,
                AmountType.Ausgabe,
                TransactionCategory.Verpflegung,
                ""));
        list.add(new Transaction("Test4",
                new Member("David",
                        "P.",
                        9421664,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                72,
                AmountType.Einnahme,
                TransactionCategory.Verpflegung,
                "Koks"));
        list.add(new Transaction("Test5",
                new Member("Fabian",
                        "K.",
                        045124,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                82,
                AmountType.Ausgabe,
                TransactionCategory.Spende,
                "Bier"));
        list.add(new Transaction("Test1",
                new Member("Steffen",
                        "B.",
                        123456,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                42,
                AmountType.Einnahme,
                TransactionCategory.Verpflegung,
                ""));
        list.add(new Transaction("Test2",
                new Member("Simoms",
                        "C.",
                        846345,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                52,
                AmountType.Einnahme,
                TransactionCategory.Spende,
                "Penispumpe"));
        list.add(new Transaction("Test3",
                new Member("Lucas",
                        "C.",
                        58245,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                62,
                AmountType.Ausgabe,
                TransactionCategory.Verpflegung,
                ""));
        list.add(new Transaction("Test4",
                new Member("David",
                        "P.",
                        9421664,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                72,
                AmountType.Einnahme,
                TransactionCategory.Verpflegung,
                "Koks"));
        list.add(new Transaction("Test5",
                new Member("Fabian",
                        "K.",
                        045124,
                        "Musterstraße 1",
                        "test@test.de"),
                new Date(),
                82,
                AmountType.Ausgabe,
                TransactionCategory.Spende,
                "Bier"));
        return list;
    }
     */

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
