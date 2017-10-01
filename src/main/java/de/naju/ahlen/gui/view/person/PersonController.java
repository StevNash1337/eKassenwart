package de.naju.ahlen.gui.view.person;

import com.j256.ormlite.dao.Dao;
import de.naju.ahlen.gui.view.base.BaseController;
import de.naju.ahlen.persistence.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
public class PersonController extends BaseController<Person, Long> {

    public PersonController() {
        view = new PersonView(this);
        form = new PersonForm(this);
    }

    @Override
    @Autowired
    public void setDao(Dao<Person, Long> personDao) {
        this.dao = personDao;
    }

    @Override
    @PostConstruct
    protected void initData() {
        updateGridItems();
    }
}
