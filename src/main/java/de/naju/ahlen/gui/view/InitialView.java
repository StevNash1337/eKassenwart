package de.naju.ahlen.gui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Steffen on 07.04.2017.
 */
@SpringView
public class InitialView extends VerticalLayout implements View {

    public InitialView(){
        addComponent(new Label("Hier entsteht die neue Präsenz des Kassenwarts der Naju Ahlen e.V."));
        addComponent(new Label("\n\nSollte kein NajuModel verfügbar sein, so muss erst eins erstellt werden. Eine entsprechende Abfrage" +
                " sollte hier zu Beginn erscheinen und ggf. eine alternative View anzeigen."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
