package de.naju.ahlen.gui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Steffen on 07.04.2017.
 */
public class FooView extends VerticalLayout implements View {

    public FooView(String text) {
        addComponent(new Label(text));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
