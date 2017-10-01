package de.naju.ahlen.gui.view.document;

import com.j256.ormlite.dao.Dao;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import de.naju.ahlen.gui.view.base.BaseController;
import de.naju.ahlen.persistence.model.Document;
import de.naju.ahlen.persistence.model.enums.DocumentFormat;
import de.naju.ahlen.util.DocumentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class DocumentController extends BaseController<Document, Long> {

    private Document beforeUpdate;

    public DocumentController() {
        view = new DocumentView(this);
        form = new DocumentForm(this);
    }

    @Override
    public void buttonEditClicked() {
        Document document = view.getSelectedItem();

        beforeUpdate = document.copy();

        form.setEntity(document);
        form.openInModalPopup();
    }

    @Override
    public void buttonDeleteClicked() {
        try {
            Document document = view.getSelectedItem();
            dao.delete(document);
            DocumentUtil.deleteDocument(document);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateGridItems();
    }

    @Override
    public void formSaved(Document document) {
        try {
            if (beforeUpdate != null && !document.getName().equals(beforeUpdate.getName())) {
                dao.createOrUpdate(document);
                DocumentUtil.moveDocument(beforeUpdate, document);
            } else if (beforeUpdate != null) {
                dao.createOrUpdate(document);
                DocumentUtil.deleteDocument(beforeUpdate);
            } else {
                dao.createOrUpdate(document);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateGridItems();
        form.closePopup();
        beforeUpdate = null;
    }
    @Override
    public void updateButtons() {
        super.updateButtons();
        if (!view.isSelected()) {
            ((DocumentView)view).setButtonShowEnabled(false);
        } else {
            ((DocumentView)view).setButtonShowEnabled(true);
        }
    }

    @Override
    @Autowired
    public void setDao(Dao<Document, Long> documentDao) {
        this.dao = documentDao;
    }

    @Override
    @PostConstruct
    protected void initData() {
        updateGridItems();
    }

    public boolean existsName(String name) {
        try {
            List<Document> documents = dao.queryForAll();
            for (Document document : documents) {
                if (document.getName().equals(name)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void buttonShowClicked() {
        System.out.println("Button show clicked");
        Document document = view.getSelectedItem();
        DocumentFormat format = document.getFormat();
        Resource data = DocumentUtil.getData(document);
        Window window = new Window();
        window.setWidth("800");
        window.setHeight("600");
        window.center();
        window.setCaption(document.getName());
        //VerticalLayout layout = new VerticalLayout();
        //layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        //window.setContent(layout);
        if (format.equals(DocumentFormat.PNG) || format.equals(DocumentFormat.JPG)) {
            Image image = new Image(document.getName(), data);
            float aspectRatio = image.getWidth()/image.getHeight();
            // funktioniert so nicht
            //image.setWidth("800");
            //image.setHeight(String.valueOf(750/aspectRatio));
            window.setContent(image);
        } else if (format.equals(DocumentFormat.PDF)) {
            Embedded embedded = new Embedded();
            embedded.setSizeFull();
            embedded.setType(Embedded.TYPE_BROWSER);
            embedded.setSource(data);
            window.setContent(embedded);
        }
        UI.getCurrent().addWindow(window);
    }
}
