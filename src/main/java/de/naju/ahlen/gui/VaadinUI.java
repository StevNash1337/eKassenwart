package de.naju.ahlen.gui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.spring.annotation.SpringUI;

@SpringUI(path = "")
@Theme("valo")
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
    }
}