package de.naju.ahlen.gui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Steffen on 28.04.2017.
 */
public class MemberView extends VerticalLayout implements View {

    public MemberView(){
        addComponent(new Label("Mitgleiderverwaltung"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
