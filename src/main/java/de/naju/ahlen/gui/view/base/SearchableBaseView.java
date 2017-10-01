package de.naju.ahlen.gui.view.base;

import org.vaadin.viritin.fields.MTextField;

public abstract class SearchableBaseView<T> extends BaseView<T> {

    private MTextField tSearch;

    public SearchableBaseView(BaseController<T, ?> controller, String title) {
        super(controller, title);

        tSearch = new MTextField();
        buttonLayout.addComponent(tSearch, 0);
    }

    public String getSearchText() {
        return tSearch.getValue();
    }
}
