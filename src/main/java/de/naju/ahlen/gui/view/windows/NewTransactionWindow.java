package de.naju.ahlen.gui.view.windows;

import com.vaadin.ui.Window;

/**
 * Created by Steffen on 29.04.2017.
 */
public class NewTransactionWindow extends Window{

    public NewTransactionWindow(String caption){
        setCaption(caption);
        setResizable(false);
        setModal(true);
        setClosable(true);
        setDraggable(false);
        center();
        setWidth("500");
        setHeight("600");
    }
}
