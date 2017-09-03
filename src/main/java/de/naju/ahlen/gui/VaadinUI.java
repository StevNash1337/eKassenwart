package de.naju.ahlen.gui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import de.naju.ahlen.gui.view.*;
import de.naju.ahlen.gui.view.bankaccount.BankAccountController;
import de.naju.ahlen.gui.view.person.PersonController;
import de.naju.ahlen.gui.view.person.PersonView;
import de.naju.ahlen.persistence.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.teemusa.sidemenu.SideMenu;

import javax.servlet.annotation.WebServlet;
import java.io.File;

@SpringUI()
@Theme("mytheme")
public class VaadinUI extends UI {

    private SideMenu sideMenu = new SideMenu();
    private boolean logoVisible = true;
    private ThemeResource logo = new ThemeResource("img" + File.separator + "Naju_Logo_Transparent_klein.png");
    private String menuCaption = "Naju Ahlen e.V.";

    @Autowired
    private PersonController personController;

    @Autowired
    private BankAccountController bankAccountController;

    //private TransactionView transactionView;


    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("eKassenwart");

        generateTestData();

        setContent(sideMenu);
        Navigator navigator = new Navigator(this, sideMenu);
        setNavigator(navigator);
        navigator.addView("", new InitialView());
//        navigator.addView("Transactions", TransactionView);
        navigator.addView("Members", personController.getPersonView());
        navigator.addView("BankAccounts", bankAccountController.getBankAccountView());
        navigator.addView("Statistics", new ChartView());
        navigator.addView("Settings", new SettingView());

        sideMenu.setMenuCaption(menuCaption);

        // Add Menus with changing the URI in Browser
        sideMenu.addNavigation("Startseite", VaadinIcons.HOME_O, "");
        //sideMenu.addNavigation("Transaktionen", VaadinIcons.MONEY_EXCHANGE, "Transactions");
        sideMenu.addNavigation("Mitglieder", VaadinIcons.MALE, "Members");
        sideMenu.addNavigation("Konten", VaadinIcons.MALE, "BankAccounts");
        sideMenu.addNavigation("Statistiken", VaadinIcons.CHART, "Statistics");
        sideMenu.addNavigation("Einstellungen", VaadinIcons.WRENCH, "Settings");



        /*
        // Add Menus without changing the URI in Browser
        // Arbitrary method execution
        sideMenu.addMenuItem("My Menu Entry", () -> {
            VerticalLayout content = new VerticalLayout();
            content.addComponent(new Label("A layout"));
            sideMenu.setContent(content);
        });

        sideMenu.addMenuItem("Entry With Icon", VaadinIcons.GOOGLE_PLUS, () -> {
            VerticalLayout content = new VerticalLayout();
            content.addComponent(new Label("Another layout"));
            sideMenu.setContent(content);
        });
        */

        setUser("", logo);
    }

    private void generateTestData() {
        Person p1 = new Person();
        p1.setFirstName("Lucas");
        p1.setLastName("Camphausen");
        p1.setCity("Ahlen");
        p1.setStreet("Im Beesenfeld");
        p1.setStreetNumber("47");
        p1.setZipCode("59227");
        Person p2 = new Person();
        p2.setFirstName("Simon");
        p2.setLastName("Camphausen");
        p2.setCity("Ahlen");
        p2.setStreet("Im Beesenfeld");
        p2.setStreetNumber("47");
        p2.setZipCode("59227");
        Person p3 = new Person();
        p3.setFirstName("David");
        p3.setLastName("Pannock");
        p3.setCity("Ahlen");
        p3.setStreet("Knappenweg");
        p3.setStreetNumber("4");
        p3.setZipCode("59229");
        personController.getPersonRepository().save(p1);
        personController.getPersonRepository().save(p2);
        personController.getPersonRepository().save(p3);
    }

    private void setUser(String name, Resource icon) {
        //sideMenu.setUserName(name);
        sideMenu.setUserIcon(icon);

        /*sideMenu.clearUserMenu();
        sideMenu.addUserMenuItem("Settings", FontAwesome.WRENCH, () -> {
            Notification.show("Showing settings", Type.TRAY_NOTIFICATION);
        });
        sideMenu.addUserMenuItem("Sign out", () -> {
            Notification.show("Logging out..", Type.TRAY_NOTIFICATION);
        });

        sideMenu.addUserMenuItem("Hide logo", () -> {
            if (!logoVisible) {
                sideMenu.setMenuCaption(menuCaption, logo);
            } else {
                sideMenu.setMenuCaption(menuCaption);
            }
            logoVisible = !logoVisible;
        });*/
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = VaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }
}