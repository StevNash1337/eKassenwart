package de.naju.ahlen.gui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.spring.annotation.SpringUI;

@SpringUI(path = "")
@Theme("mytheme")
public class VaadinUI extends UI {

    private HorizontalSplitPanel wrapper;
    private VerticalLayout menuWrapper;
    private VerticalLayout contenHomeWrapper;

    @Override
    protected void init(VaadinRequest request) {
        wrapper = new HorizontalSplitPanel();
        wrapper.setSizeFull();
        wrapper.setSplitPosition(280, Unit.PIXELS);
        wrapper.setLocked(true);

        menuWrapper = new VerticalLayout();
        menuWrapper.setWidth(100, Unit.PERCENTAGE);
        menuWrapper.setMargin(true);
        menuWrapper.setSpacing(true);

        contenHomeWrapper = new VerticalLayout();
        contenHomeWrapper.setWidth(100, Unit.PERCENTAGE);

        initMenu();

        Button b1 = new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!", Notification.Type.TRAY_NOTIFICATION));
        contenHomeWrapper.addComponent(b1);

        wrapper.addComponent(menuWrapper);
        wrapper.addComponent(contenHomeWrapper);
        setContent(wrapper);
    }

    private void initMenu(){
        // Button Styling: https://vaadin.com/forum#!/thread/1687729
        Button bHome = new Button("Home");
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

        menuWrapper.addComponents(bHome, bFinance, bMembers, bSettings);
    }
}