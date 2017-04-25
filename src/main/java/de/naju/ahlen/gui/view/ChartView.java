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
public class ChartView extends VerticalLayout implements View {

    public ChartView(){
        addComponent(new Label("Hier sollen bald ein paar Charts hin"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
