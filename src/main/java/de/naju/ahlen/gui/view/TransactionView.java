package de.naju.ahlen.gui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Steffen on 25.04.2017.
 */
@SpringView
public class TransactionView extends VerticalLayout implements View {

    public TransactionView(){
        addComponent(new Label("Money Money Money!!!11^111einself"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
