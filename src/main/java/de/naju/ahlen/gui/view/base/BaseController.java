package de.naju.ahlen.gui.view.base;

import com.j256.ormlite.dao.Dao;
import de.naju.ahlen.Application;
import de.naju.ahlen.persistence.model.OnlineAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.SQLException;

@Controller
public abstract class BaseController<T, ID> {

    protected BaseView<T> view;
    protected BaseForm<T> form;

    protected Dao<T, ID> dao;

    protected Class<T> entityType;

    protected BaseController() {
        entityType = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public abstract void setDao(Dao<T, ID> dao);

    public BaseView<T> getView() {
        return view;
    }

    public void updateGridItems() {
        try {
            view.setGridItems(dao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateButtons();
    }

    public void buttonNewClicked() {
        Constructor<T> c = null;
        try {
            c = entityType.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        c.setAccessible(true);
        try {
            form.setEntity(c.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        form.openInModalPopup();
    }

    public void buttonEditClicked() {
        form.setEntity(view.getSelectedItem());
        form.openInModalPopup();
    }

    public void buttonDeleteClicked() {
        try {
            dao.delete(view.getSelectedItem());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateGridItems();
    }

    public void updateButtons() {
        if (!view.isSelected()) {
            view.setButtonEditEnabled(false);
            view.setButtonDeleteEnabled(false);
        } else {
            view.setButtonEditEnabled(true);
            view.setButtonDeleteEnabled(true);
        }
    }

    public void formSaved(T entity) {
        try {
            dao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateGridItems();
        form.closePopup();
    }

    public void formCanceled() {
        updateGridItems();
        form.closePopup();
    }

    protected abstract void initData();
}
