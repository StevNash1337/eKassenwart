package de.naju.ahlen.gui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.spring.annotation.SpringUI;
import de.naju.ahlen.gui.ui.MenuWrapper;

@SpringUI(path = "")
@Theme("mytheme")
public class VaadinUI extends UI {

    private HorizontalSplitPanel wrapper;
    private MenuWrapper menuWrapper;
    private VerticalLayout contenHomeWrapper;

    public Navigator navigator;
    protected static final String MAINVIEW = "main";

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Navigation Example");

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
        Navigator.ComponentContainerViewDisplay viewDisplay = new Navigator.ComponentContainerViewDisplay(layout);

        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        //navigator = new Navigator(UI.getCurrent(), viewDisplay);

        // Create and register the views
        navigator.addView("", new StartView());
        navigator.addView(MAINVIEW, new MainView());
    }

    private void tmp(){
        wrapper = new HorizontalSplitPanel();
        wrapper.setSizeFull();
        wrapper.setSplitPosition(280, Unit.PIXELS);
        wrapper.setLocked(true);

        menuWrapper = new MenuWrapper();
        menuWrapper.setWidth(100, Unit.PERCENTAGE);
        menuWrapper.setMargin(true);
        menuWrapper.setSpacing(true);

        contenHomeWrapper = new VerticalLayout();
        contenHomeWrapper.setWidth(100, Unit.PERCENTAGE);

        Button b1 = new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!", Notification.Type.TRAY_NOTIFICATION));
        contenHomeWrapper.addComponent(b1);

        wrapper.addComponent(menuWrapper);
        wrapper.addComponent(contenHomeWrapper);
        setContent(wrapper);
    }


}