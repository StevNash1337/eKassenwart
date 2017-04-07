package de.naju.ahlen.gui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import de.naju.ahlen.gui.view.FooView;
import org.vaadin.teemusa.sidemenu.SideMenu;
import org.vaadin.teemusa.sidemenu.SideMenuUI;

import javax.servlet.annotation.WebServlet;
import java.io.File;


@SpringUI(path = "")
@Theme("mytheme")
@SideMenuUI
public class VaadinUI extends UI {

    private SideMenu sideMenu = new SideMenu();
    private boolean logoVisible = true;
    private ThemeResource logo = new ThemeResource("img" + File.separator + "Naju_Logo_Transparent_klein.png");
    private String menuCaption = "Naju Ahlen e.V.";

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("eKassenwart");

        setContent(sideMenu);
        Navigator navigator = new Navigator(this, sideMenu);
        setNavigator(navigator);
        navigator.addView("", new FooView("Initial view"));
        navigator.addView("Foo", new FooView("Foo!mgdmndtndtdbdbdbdr"));

        //sideMenu.setMenuCaption(menuCaption, logo);
        sideMenu.setMenuCaption(menuCaption);

        // Add Menus with changing the URI in Browser
        // Navigation examples
        sideMenu.addNavigation("Initial View", "");
        sideMenu.addNavigation("Secondary View gfsgsgsr egs", FontAwesome.AMBULANCE, "Foo");

        // Add Menus without changing the URI in Browser
        // Arbitrary method execution
        sideMenu.addMenuItem("My Menu Entry", () -> {
            VerticalLayout content = new VerticalLayout();
            content.addComponent(new Label("A layout"));
            sideMenu.setContent(content);
        });
        sideMenu.addMenuItem("Entry With Icon", FontAwesome.ANDROID, () -> {
            VerticalLayout content = new VerticalLayout();
            content.addComponent(new Label("Another layout"));
            sideMenu.setContent(content);
        });

        setUser("", logo);
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