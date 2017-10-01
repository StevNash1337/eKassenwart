package de.naju.ahlen.gui.view.base;

import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import de.naju.ahlen.gui.extensions.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;


public abstract class BaseForm<T> extends AbstractForm<T> {

    protected BaseController<T, ?> controller;

    protected Binder<T> binder;

    @SuppressWarnings("unchecked")
    public BaseForm(BaseController<T, ?> controller) {
        super(controller.entityType);
        this.controller = controller;

        binder = new Binder<>(controller.entityType);

        setBinder(binder);

        setSavedHandler((SavedHandler<T>) entity -> controller.formSaved(entity));

        setResetHandler((ResetHandler<T>) entity -> controller.formCanceled());

        setSizeUndefined();
    }

    protected Field[] getFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        return fields;
    }

    @Override
    protected Component createContent() {

        Field[] fields = getFields();
        List<Component> components = new LinkedList<>();
        //Component[] instances = new Component[fields.length];
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object z = field.get(this);
                components.add((Component) z);
                //instances[i] = (Component) z;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
            }
        }

        Component[] instances = new Component[components.size()];
        for (int i = 0; i < instances.length; i++) {
            instances[i] = components.get(i);
        }

        return new MVerticalLayout(
                new MFormLayout(
                        instances
                ).withWidth(""), getToolbar()
        ).withWidth("");
    }
}
