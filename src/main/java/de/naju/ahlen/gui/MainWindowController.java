package de.naju.ahlen.gui;

import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.UI;
import de.naju.ahlen.gui.extensions.MySideMenu;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents a controller for the main window. It holds the sidemenu and instanciates each menu item.
 */
@Controller
public class MainWindowController {

    private MySideMenu sideMenu;

    private List<MyMenuItem> root;

    private MyMenuItem personMenuItem;

    private MyMenuItem financeMenuItem;
    private MyMenuItem transactionMenu;
    private MyMenuItem accountMenuItem;
    private MyMenuItem cashAccountMenuItem;
    private MyMenuItem bankAccountMenuItem;
    private MyMenuItem onlineAccountMenuItem;
    private MyMenuItem categoryMenuItem;

    private MyMenuItem documnetMenuItem;

    private MyMenuItem projectMenuItem;


    public MainWindowController() {
        root = new LinkedList<>();

        sideMenu = new MySideMenu();

        sideMenu.setMenuCaption("eKassenwart");
        sideMenu.setUserMenuVisible(false);

        sideMenu.setMenuAreaWidth((float) 5.5, Sizeable.Unit.CM);

        personMenuItem = new MyMenuItem(this, "Mitglieder",
                (MySideMenu.MenuClickHandler) () -> UI.getCurrent().getNavigator().navigateTo("persons"));

        financeMenuItem = new MyMenuItem(this, "Finanzen",
                (MySideMenu.MenuClickHandler) () -> financeMenuItem.toggleChildren());

        transactionMenu = new MyMenuItem(this, "Buchungen",
                (MySideMenu.MenuClickHandler) () -> UI.getCurrent().getNavigator().navigateTo("transactions"));

        accountMenuItem = new MyMenuItem(this, "Konten",
                (MySideMenu.MenuClickHandler) () -> {
                    UI.getCurrent().getNavigator().navigateTo("accounts");
                    accountMenuItem.toggleChildren();});

        cashAccountMenuItem = new MyMenuItem(this, "Barkonten",
                (MySideMenu.MenuClickHandler) () -> UI.getCurrent().getNavigator().navigateTo("cashAccounts"));

        bankAccountMenuItem = new MyMenuItem(this, "Bankkonten",
                (MySideMenu.MenuClickHandler) () -> UI.getCurrent().getNavigator().navigateTo("bankAccounts"));

        onlineAccountMenuItem = new MyMenuItem(this, "Onlinekonten",
                (MySideMenu.MenuClickHandler) () -> UI.getCurrent().getNavigator().navigateTo("onlineAccounts"));

        categoryMenuItem = new MyMenuItem(this, "Kategorien",
                (MySideMenu.MenuClickHandler) () -> UI.getCurrent().getNavigator().navigateTo("categories"));

        documnetMenuItem = new MyMenuItem(this, "Dokumente",
                (MySideMenu.MenuClickHandler) () -> UI.getCurrent().getNavigator().navigateTo("documents"));

        projectMenuItem = new MyMenuItem(this, "Projekte",
                (MySideMenu.MenuClickHandler) () -> UI.getCurrent().getNavigator().navigateTo("projects"));

        root.add(personMenuItem);
        root.add(financeMenuItem);
        root.add(documnetMenuItem);
        root.add(projectMenuItem);
        financeMenuItem.addChild(transactionMenu);
        financeMenuItem.addChild(accountMenuItem);
        financeMenuItem.addChild(categoryMenuItem);
        accountMenuItem.addChild(cashAccountMenuItem);
        accountMenuItem.addChild(bankAccountMenuItem);
        accountMenuItem.addChild(onlineAccountMenuItem);
    }

    @Bean
    public MySideMenu mySideMenu() {
        return sideMenu;
    }

    public void setMenuCaption(String menuCaption) {
        sideMenu.setMenuCaption(menuCaption);
    }

    public void setMenuCaption(String menuCaption, FileResource logo){
        if (logo == null) {
            sideMenu.setMenuCaption(menuCaption);
        } else {
            sideMenu.setMenuCaption(menuCaption, logo);
        }
    }

    public void showMenuItems() {
        cleanMenu();
        refreshMenu();
    }

    public void hideMenuItemsExceptProject() {
        cleanMenu();
        projectMenuItem.show();
    }

    /**
     * Wird aufgerufen, wen ein Projekt gelöscht wird, das gerade aktiv ist.
     * Icon und Name werden auf Standardwerte gesetzt.
     */
    public void resetSideMenu() {
        sideMenu.setMenuCaption("eKassenwart");
    }

    public void refreshMenu() {
        cleanMenu();
        for (MyMenuItem item : root) {
            refreshMenu(item);
        }
    }

    private void refreshMenu(MyMenuItem item) {
        if (item.isVisible()) {
            item.show();
            for (MyMenuItem child : item.getChildren()) {
                refreshMenu(child);
            }
        }
    }

    private void cleanMenu() {
        if (root != null) {
            for (MyMenuItem item : root) {
                cleanMenu(item);
            }
        }
    }

    private void cleanMenu(MyMenuItem item) {
        item.hide();
        for (MyMenuItem child : item.getChildren()) {
            child.hide();
            cleanMenu(child);
        }
    }
}
