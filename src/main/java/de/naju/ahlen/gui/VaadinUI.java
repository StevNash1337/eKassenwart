package de.naju.ahlen.gui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import de.naju.ahlen.gui.view.account.AccountController;
import de.naju.ahlen.gui.view.account.bankaccount.BankAccountController;
import de.naju.ahlen.gui.view.account.cashaccount.CashAccountController;
import de.naju.ahlen.gui.view.account.onlineaccount.OnlineAccountController;
import de.naju.ahlen.gui.view.category.CategoryController;
import de.naju.ahlen.gui.view.document.DocumentController;
import de.naju.ahlen.gui.view.person.PersonController;
import de.naju.ahlen.gui.view.project.ProjectController;
import de.naju.ahlen.gui.view.transaction.TransactionController;
import org.springframework.beans.factory.annotation.Autowired;
import de.naju.ahlen.gui.extensions.MySideMenu;

import javax.servlet.annotation.WebServlet;
import java.io.File;

/**
 * The main ui class.
 */
@SpringUI()
@Theme("mytheme")
@PreserveOnRefresh
public class VaadinUI extends UI {

    @Autowired
    private MySideMenu sideMenu;

    @Autowired
    private MainWindowController mainWindowController;

    @Autowired
    private PersonController personController;

    @Autowired
    private AccountController accountController;

    @Autowired
    private CashAccountController cashAccountController;

    @Autowired
    private BankAccountController bankAccountController;

    @Autowired
    private OnlineAccountController onlineAccountController;

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private ProjectController projectController;

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private DocumentController documentController;

    //private SideMenu sideMenu = new SideMenu();
    private boolean logoVisible = true;
    private ThemeResource logo = new ThemeResource("img" + File.separator + "Naju_Logo_Transparent_klein.png");
    private String menuCaption = "Naju Ahlen e.V.";


    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("eKassenwart");

        Navigator navigator = new Navigator(this, sideMenu);

        navigator.addView("", projectController.getView());
        navigator.addView("persons", personController.getView());
        navigator.addView("transactions", transactionController.getView());
        navigator.addView("documents", documentController.getView());
        navigator.addView("accounts", accountController.getView());
        navigator.addView("cashAccounts", cashAccountController.getView());
        navigator.addView("bankAccounts", bankAccountController.getView());
        navigator.addView("onlineAccounts", onlineAccountController.getView());
        navigator.addView("categories", categoryController.getView());
        navigator.addView("projects", projectController.getView());


        mainWindowController.hideMenuItemsExceptProject();
        //mainWindowController.setMenuCaption("Lol e.V.", logo);

        setNavigator(navigator);
        setContent(sideMenu);
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = VaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }
}