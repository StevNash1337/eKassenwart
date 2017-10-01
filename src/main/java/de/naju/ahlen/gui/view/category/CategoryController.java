package de.naju.ahlen.gui.view.category;

import com.j256.ormlite.dao.Dao;
import de.naju.ahlen.gui.view.base.BaseController;
import de.naju.ahlen.persistence.model.Category;
import de.naju.ahlen.persistence.model.enums.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class CategoryController extends BaseController<Category, Long> {

    public CategoryController() {
        view = new CategoryView(this);
        form = new CategoryForm(this);
    }

    @Override
    @Autowired
    public void setDao(Dao<Category, Long> categoryDao) {
        this.dao = categoryDao;
    }

    @Override
    @PostConstruct
    protected void initData() {
        updateGridItems();
    }

    public boolean existsType(Optional<CategoryType> type, String value) {
        if (!type.isPresent()) {
            return false;
        }

        try {
            for (Category category : dao.queryForAll()) {
                if (category.getType().equals(type.get()) && category.getValue().equals(value)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
