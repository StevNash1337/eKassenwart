package de.naju.ahlen.gui.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Steffen on 03.04.2017.
 */
public class MenuWrapper extends VerticalLayout {

    public MenuWrapper(){
        initMenu();
    }

    private void initMenu(){
        // Button Styling: https://vaadin.com/forum#!/thread/1687729
        Button bHome = new Button("Home");
        bHome.setHeight("80px");
        bHome.setStyleName("menu-button");
        bHome.addClickListener(event -> {
            Notification.show("bHome was clicked", Notification.Type.TRAY_NOTIFICATION);
        });

        Button bFinance = new Button("Einnahmen/Ausgaben");
        bFinance.setStyleName("menu-button");
        bFinance.addClickListener(event -> {
            Notification.show("bFinance was clicked", Notification.Type.TRAY_NOTIFICATION);
        });

        Button bMembers = new Button("Mitglieder");
        bMembers.setStyleName("menu-button");
        bMembers.addClickListener(event -> {
            Notification.show("bMembers was clicked", Notification.Type.TRAY_NOTIFICATION);
        });

        Button bSettings = new Button("Einstellungen");
        bSettings.setStyleName("menu-button");
        bSettings.addClickListener(event -> {
            Notification.show("bSettings was clicked", Notification.Type.TRAY_NOTIFICATION);
        });

        this.addComponents(bHome, bFinance, bMembers, bSettings);
    }
}
