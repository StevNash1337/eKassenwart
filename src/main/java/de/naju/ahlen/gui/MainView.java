package de.naju.ahlen.gui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import javax.management.NotificationFilter;

import static de.naju.ahlen.gui.VaadinUI.MAINVIEW;

/** Main view with a menu (with declarative layout design) */
public class MainView extends VerticalLayout implements View {

    public MainView() {
        //Design.read(this);

        setSizeFull();
        setSpacing(true);
        addComponent(new Label("Main"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("MainView :) ", Notification.Type.TRAY_NOTIFICATION);
    }
}