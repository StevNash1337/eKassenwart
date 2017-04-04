package de.naju.ahlen.gui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import static de.naju.ahlen.gui.VaadinUI.MAINVIEW;

/** A start view for navigating to the main view */
public class StartView extends VerticalLayout implements View {

    public StartView() {
        setSizeFull();

        Button button = new Button("Go to Main View",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        getUI().getNavigator().navigateTo(MAINVIEW);
                    }
                });
        addComponent(button);
        addComponent(new Label("StartView :)"));
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the StartView!", Notification.Type.TRAY_NOTIFICATION);
    }
}
